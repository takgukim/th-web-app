package com.developerleetaehee.th_web_app.controller.board;

import com.developerleetaehee.th_web_app.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/notice")
    public String test(Model model) {
        model.addAttribute("title", "공지사항");

        return "board/notice_list";
    }

}
