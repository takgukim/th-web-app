package com.developerleetaehee.th_web_app.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/sign_up")
    public String test(Model model) {

        model.addAttribute("title", "회원가입");
        
        return "user/sign_write";
    }
    
}