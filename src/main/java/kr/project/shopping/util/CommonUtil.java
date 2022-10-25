package kr.project.shopping.util;

import kr.project.shopping.dto.user.UserSaveDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
@Slf4j
public class CommonUtil {

    // 이메일 검증
    public boolean isEmail(UserSaveDto dto) {

        // 나중에 메소드로 따로 만들기
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(dto.getUserId());

        log.info("이메일 형식 검증 여부 : {}", m.matches());

        if (!m.matches()) {
            return false;
        }

        return true;
    }
}
