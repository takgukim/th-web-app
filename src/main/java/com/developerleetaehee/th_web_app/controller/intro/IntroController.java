package com.developerleetaehee.th_web_app.controller.intro;

import com.developerleetaehee.th_web_app.common.MenuCustomCode;
import com.developerleetaehee.th_web_app.common.MenuInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/intro")
public class IntroController {

    private final MenuCustomCode menuCustomCode;

    @GetMapping("/company")
    public String showCompany(Model model) {

        MenuInfo menuInfo = menuCustomCode.getCode().get("company_intro");

        if (menuInfo == null) {
            throw new IllegalArgumentException("존재하지 않는 게시판 유형입니다. 관리자에게 문의하세요.");
        }

        model.addAttribute("title", menuInfo.getTitle());
        model.addAttribute("menuCustomCode", menuInfo);

        return "intro/company_intro";
    }
}
