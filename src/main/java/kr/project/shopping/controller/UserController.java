package kr.project.shopping.controller;


import kr.project.shopping.dto.UserSaveDto;
import kr.project.shopping.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

        System.out.println(userIdx);

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
}
