package com.developerleetaehee.th_web_app.controller.board;

import com.developerleetaehee.th_web_app.domain.Board;
import com.developerleetaehee.th_web_app.dto.board.BoardCustomConfig;
import com.developerleetaehee.th_web_app.dto.board.BoardInfo;
import com.developerleetaehee.th_web_app.dto.board.BoardViewResponse;
import com.developerleetaehee.th_web_app.dto.board.BoardSearchRequest;
import com.developerleetaehee.th_web_app.service.board.BoardService;
import com.developerleetaehee.th_web_app.utility.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/boards")
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

        List<BoardViewResponse> boards = boardService.findAll(boardSearchRequest)
                        .stream()
                        .map(BoardViewResponse::new)
                        .toList();

        // 페이징 정보 조회
        String prefixFile = boardInfo.getPrefixFile();
        String pageUrl = String.format("/boards/%s/posts", prefixFile);

        Page<Board> boardPage = boardService.getBoardPage(boardSearchRequest);
        String queryString = PaginationUtil.buildQueryStringFromMap(searchParamMap);
        Map<String,Object> pages = PaginationUtil.buildPage(boardPage, startPage, perPage, pageUrl, queryString);

        model.addAttribute("title", boardInfo.getPageTitle());
        model.addAttribute("boards", boards);
        model.addAttribute("boardType", type);
        model.addAttribute("pages", pages);
        model.addAttribute("searchParamMap", searchParamMap);

        return String.format("board/%s_list", prefixFile);
    }

    @GetMapping("/{type}/posts/{$id}")
    public String findAllBoards(
            @PathVariable String type,
            Model model) {

        BoardInfo boardInfo = boardCustomConfig.getConfig().get(type);

        if (boardInfo == null) {
            throw new IllegalArgumentException("존재하지 않는 게시판 타입입니다: " + type);
        }

        // 파일 prefix 정보 조회
        String prefixFile = boardInfo.getPrefixFile();

        return String.format("board/%s_read", prefixFile);
    };
}
