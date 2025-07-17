package com.developerleetaehee.th_web_app.controller;

import com.developerleetaehee.th_web_app.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/notice")
    public String test(Model model) {
        model.addAttribute("title", "공지사항");

        return "board/notice_list";
    }

}
