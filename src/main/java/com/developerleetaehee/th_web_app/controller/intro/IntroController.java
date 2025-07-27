package com.developerleetaehee.th_web_app.controller.intro;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/intro")
public class IntroController {

    @GetMapping("/company")
    public String introCompany(Model model) {
        model.addAttribute("title", "회사소개");

        return "intro/company_intro";
    }
}
