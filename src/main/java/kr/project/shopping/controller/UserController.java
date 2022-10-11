package kr.project.shopping.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/signup")
    public String saveUser() {
        return "user/signup";
    }
}
