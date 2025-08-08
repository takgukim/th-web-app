package com.developerleetaehee.th_web_app.controller.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/counsel")
public class CounselController {

    @GetMapping("/write")
    public String writeCounsel(Model model) {
        model.addAttribute("title", "상담신청");

        return "board/counsel_write";
    }
}
