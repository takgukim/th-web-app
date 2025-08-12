package com.developerleetaehee.th_web_app.controller.board;

import com.developerleetaehee.th_web_app.dto.counsel.CounselCustomCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/counsel")
public class CounselController {

    @Autowired
    private CounselCustomCode counselCustomCode;

    @GetMapping("/write")
    public String writeForm(Model model) {

        model.addAttribute("title", "상담신청");
        model.addAttribute("counselMethodMap", counselCustomCode.getCounselMethodMap());
        model.addAttribute("counselKindMap", counselCustomCode.getCounselKindMap());

        return "board/counsel_write";
    }
}
