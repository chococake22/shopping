package kr.project.shopping.controller;


import kr.project.shopping.domain.user.User;
import kr.project.shopping.dto.user.PwdChangeDto;
import kr.project.shopping.dto.user.UserSaveDto;
import kr.project.shopping.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;


    @GetMapping("/signup")
    public String saveUser() {
        return "user/signup";
    }

    @PostMapping("/signup")
    @ResponseBody
    public Map<String, Object> saveUserCheck(UserSaveDto dto) {
        Long userIdx = userService.INSERT_USER(dto);
        Map<String, Object> map = new HashMap<>();
        map.put("userIdx", userIdx);

        return map;
    }

    @PostMapping("/idcheck")
    @ResponseBody
    public Map<String, Object> userIdCheck(String userId) {
        int idCount = userService.IF_USER_EXIST(userId);
        if (idCount > 0) {
            throw new RuntimeException("이미 있는 아이디입니다.");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        return map;
    }

    // 마이페이지
    @GetMapping("/mypage")
    public String mypage(Model model, Principal principal) {
        User user = userService.SELECT_USER_BY_USERID(principal.getName());
        model.addAttribute("user", user);
        return "user/mypage";
    }

    // 비밀번호 변경
    @PostMapping("change/password")
    @ResponseBody
    public Map<String, Object> changePwd(PwdChangeDto dto) {
        return userService.UPDATE_PWD(dto);
    }

//    // 카카오 로그인
//    @PostMapping("/auth/kakao/callback")
//    @ResponseBody
//    public Map<String, Object> kakaoLogin(String code) {
//        System.out.println("카카오 인증 시도");
//        System.out.println("code : " + code);
//        return kakaoService.getUserInfo(code);
//    }
}
