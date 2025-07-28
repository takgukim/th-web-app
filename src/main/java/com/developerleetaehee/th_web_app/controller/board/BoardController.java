package com.developerleetaehee.th_web_app.controller.board;

import com.developerleetaehee.th_web_app.domain.Board;
import com.developerleetaehee.th_web_app.dto.board.BoardListViewResponse;
import com.developerleetaehee.th_web_app.dto.board.BoardSearchRequest;
import com.developerleetaehee.th_web_app.service.board.BoardService;
import com.developerleetaehee.th_web_app.utility.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/notice")
    public String findAllNotices(
            @RequestParam(name = "start_page", defaultValue = "0") int startPage,
            @RequestParam(name = "per_page", defaultValue = "10") int perPage,
            Model model) {

        BoardSearchRequest boardSearchRequest = new BoardSearchRequest();
        boardSearchRequest.setPageRange(startPage, perPage);
        boardSearchRequest.setBoardType("notice");

        List<BoardListViewResponse> boards = boardService.findAll(boardSearchRequest)
                        .stream()
                        .map(BoardListViewResponse::new)
                        .toList();

        Page<Board> boardPage = boardService.getBoardPage(boardSearchRequest);

        Map<String,Object> pages = PaginationUtil.buildPage(boardPage, startPage, perPage);

        model.addAttribute("title", "공지사항");
        model.addAttribute("boards", boards);
        model.addAttribute("pages", pages);

        return "board/notice_list";
    }
}
