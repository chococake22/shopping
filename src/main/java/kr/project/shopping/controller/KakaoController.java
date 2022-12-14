package kr.project.shopping.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;
import java.util.HashMap;

@Controller
@RequiredArgsConstructor
public class KakaoController {

    @GetMapping("/login/kakao")
    @ResponseBody
    public String kakaoLogin(HttpServletRequest request) {

        // Redirect로 설정된 메소드가 바로 이 메소드다.
        // 이 url로 요청을 보낸다.(바로 https://kauth.kakao.com/oauth/authorize 여기에다가 요첨함 / 뒤에 parameter를 같이 보내서)
        // 그러면 response_type으로 code를 받게 되는데
        // 여기서 받은 코드로 바로 아래 있는 oauthKakao 메소드를 호출한다.
        String reqUrl = "https://kauth.kakao.com/oauth/authorize"
                + "?client_id=5496be38ca50070af71eab2dcbc6ec26"
                + "&redirect_uri=http://localhost:8080/oauth/kakao"
                + "&response_type=code";

        return reqUrl;
    }

    // 로그인하기
    @GetMapping("/oauth/kakao")
    public String oauthKakao(@RequestParam(value = "code", required = false) String code,
                             Model model, HttpSession session) {

        System.out.println("code : " + code);

        String accessToken = getAccessToken(code);
        HashMap<String, Object> userInfo = getUserInfo(accessToken);
        System.out.println("accessToken : " + accessToken);

        // 세션에 액세스 토큰과 닉네임 저장
        session.setAttribute("access_token", accessToken);
        session.setAttribute("nickname", userInfo.get("nickname"));

        System.out.println("닉네임 : " + session.getAttribute("nickname"));

        String username = (String) userInfo.get("nickname");
        String password = (String) userInfo.get("nickname") + accessToken;

        model.addAttribute("user", username);

        return "redirect:/";
    }

    // 액세스 토큰 가져오기
    // 정해진 url로 code와 함께 보내면 거기서 액세스 토큰과 리프레시 토큰을 발급해준다.
    // 여기서는 리턴으로 액세스 토큰을 받는다.
    public String getAccessToken(String code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=5496be38ca50070af71eab2dcbc6ec26");
            sb.append("&redirect_uri=http://localhost:8080/oauth/kakao");
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            // 결과 코드가 200이라면 성공한 것
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            // 요청을 통해 얻은 JSON 타입의 response 메시지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            System.out.println("response Body : " + result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_Token : " + refresh_Token);

            bw.close();
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return access_Token;
    }

    // 유저정보 조회하기
    public HashMap<String, Object> getUserInfo(String access_Token) {

        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        try {

            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 요청에 필요한 내용들을 header에 넣는다
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
            System.out.println("유저 정보 조회용 responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            System.out.println("response Body : " + result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String nickname = properties.getAsJsonObject().get("nickname").getAsString();


            userInfo.put("accessToken", access_Token);
            userInfo.put("nickname", nickname);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return userInfo;

    }


    @GetMapping("/logout/kakao")
    public String logout(HttpSession session) {

        String access_Token = (String) session.getAttribute("access_token");
        System.out.println(access_Token);

        if (access_Token != null && !access_Token.equals("")) {
            kakaoLogout(access_Token);
            session.removeAttribute("access_token");
            session.removeAttribute("nickname");
            session.invalidate();
        } else {
            System.out.println("access_Token is null");
        }

        System.out.println("로그아웃 되엇씁니다.");

        return "redirect:/";
    }

    public void kakaoLogout(String access_Token) {
        String reqURL = "https://kapi.kakao.com/v1/user/logout";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String result = "";
            String line = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
