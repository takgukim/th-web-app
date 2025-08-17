package com.developerleetaehee.th_web_app.controller.user;

import com.developerleetaehee.th_web_app.common.MenuCustomCode;
import com.developerleetaehee.th_web_app.common.MenuInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final MenuCustomCode menuCustomCode;

    @GetMapping("/sign_up")
    public String signupForm(Model model) {

        MenuInfo menuInfo = menuCustomCode.getCode().get("sign_write");

        if (menuInfo == null) {
            throw new IllegalArgumentException("존재하지 않는 게시판 유형입니다. 관리자에게 문의하세요.");
        }

        model.addAttribute("title", menuInfo.getTitle());
        model.addAttribute("menuCustomCode", menuInfo);
        
        return "user/sign_write";
    }
    
}