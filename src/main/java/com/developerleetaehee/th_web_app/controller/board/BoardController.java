package com.developerleetaehee.th_web_app.controller.board;

import com.developerleetaehee.th_web_app.domain.Board;
import com.developerleetaehee.th_web_app.dto.board.*;
import com.developerleetaehee.th_web_app.service.board.BoardService;
import com.developerleetaehee.th_web_app.utility.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    private BoardCustomConfig boardCustomConfig;

    @GetMapping("/{type}/posts")
    public String findAllBoards(
            @RequestParam(name = "start_page", defaultValue = "0") int startPage,
            @RequestParam(name = "per_page", defaultValue = "10") int perPage,
            @RequestParam(name = "search_subject", defaultValue = "") String searchSubject,
            @RequestParam(name = "search_writer", defaultValue = "") String searchWriter,
            @RequestParam(name = "search_start_date", defaultValue = "") String searchStartDate,
            @RequestParam(name = "search_end_date", defaultValue = "") String searchEndDate,
            @PathVariable String type,
            Model model) {

        BoardInfo boardInfo = boardCustomConfig.getConfig().get(type);

        if (boardInfo == null) {
            throw new IllegalArgumentException("존재하지 않는 게시판 타입입니다: " + type);
        }

        if (searchStartDate == null || searchStartDate.isBlank()) {
            searchStartDate = LocalDate.now().minusMonths(1).toString();
        }
        if (searchEndDate == null || searchEndDate.isBlank()) {
            searchEndDate = LocalDate.now().toString();
        }

        BoardSearchRequest boardSearchRequest = new BoardSearchRequest();
        boardSearchRequest.setPageRange(startPage, perPage);
        boardSearchRequest.setBoardType(type);
        boardSearchRequest.setSubject(searchSubject);
        boardSearchRequest.setWriter(searchWriter);
        boardSearchRequest.setSearchStartDate(searchStartDate);
        boardSearchRequest.setSearchEndDate(searchEndDate);

        Map<String, Object> searchParamMap = new HashMap<>();
        searchParamMap.put("search_start_date", "");
        searchParamMap.put("search_end_date", "");
        searchParamMap.put("search_subject", "");
        searchParamMap.put("search_writer", "");

        if (StringUtils.hasText(searchStartDate)) {
            searchParamMap.put("search_start_date", searchStartDate);
        }

        if (StringUtils.hasText(searchEndDate)) {
            searchParamMap.put("search_end_date", searchEndDate);
        }

        if (StringUtils.hasText(searchSubject)) {
            searchParamMap.put("search_subject", searchSubject);
        }

        if (StringUtils.hasText(searchWriter)) {
            searchParamMap.put("search_writer", searchWriter);
        }

        List<BoardListViewResponse> boards = boardService.findAll(boardSearchRequest)
                        .stream()
                        .map(BoardListViewResponse::new)
                        .toList();

        // 페이징 정보 조회
        String pageUrl = String.format("/board/%s/posts", type);

        Page<Board> boardPage = boardService.getBoardPage(boardSearchRequest);
        String queryString = PaginationUtil.buildQueryStringFromMap(searchParamMap);
        Map<String,Object> pages = PaginationUtil.buildPage(boardPage, startPage, perPage, pageUrl, queryString);

        model.addAttribute("title", boardInfo.getPageTitle());
        model.addAttribute("boards", boards);
        model.addAttribute("boardType", type);
        model.addAttribute("boardCustomConfig", boardInfo);
        model.addAttribute("pages", pages);
        model.addAttribute("searchParamMap", searchParamMap);

        return String.format("board/%s_list", boardInfo.getPrefixFile());
    }

    @GetMapping("/{type}/posts/{id}")
    public String findAllBoards(
            @PathVariable String type,
            @PathVariable Long id,
            Model model) {

        BoardInfo boardInfo = boardCustomConfig.getConfig().get(type);

        if (boardInfo == null) {
            throw new IllegalArgumentException("존재하지 않는 게시판 타입입니다: " + type);
        }

        // 조회수 업데이트 하면서 상세 내용 조회
        Board board = boardService.findAndIncreaseReadCount(id);

        model.addAttribute("title", boardInfo.getPageTitle());
        model.addAttribute("boardCustomConfig", boardInfo);
        model.addAttribute("board", new BoardViewResponse(board));

        return String.format("board/%s_read", boardInfo.getPrefixFile());
    };

    @GetMapping("/{type}/posts/new")
    public String writeForm(@PathVariable String type, Model model) {

        BoardInfo boardInfo = boardCustomConfig.getConfig().get(type);

        if (boardInfo == null) {
            throw new IllegalArgumentException("존재하지 않는 게시판 타입입니다: " + type);
        }

        model.addAttribute("title", boardInfo.getPageTitle());
        model.addAttribute("pageSubTitle", "작성 화면");
        model.addAttribute("boardType", type);
        model.addAttribute("boardCustomConfig", boardInfo);
        model.addAttribute("board", Board.builder().build()); // 초기화 안하면 등록에서 못불러옴

        return String.format("board/%s_write", boardInfo.getPrefixFile());
    }

    @GetMapping("/{type}/posts/edit/{id}")
    public String editForm(
            @PathVariable String type,
            @PathVariable long id,
            Model model) {

        BoardInfo boardInfo = boardCustomConfig.getConfig().get(type);

        if (boardInfo == null) {
            throw new IllegalArgumentException("존재하지 않는 게시판 타입입니다: " + type);
        }

        // 조회수 업데이트 하면서 상세 내용 조회
        Board board = boardService.findAndIncreaseReadCount(id);

        model.addAttribute("title", boardInfo.getPageTitle());
        model.addAttribute("pageSubTitle", "수정 화면");
        model.addAttribute("boardType", type);
        model.addAttribute("boardCustomConfig", boardInfo);
        model.addAttribute("board", new BoardViewResponse(board));

        return String.format("board/%s_write", boardInfo.getPrefixFile());
    }
}
