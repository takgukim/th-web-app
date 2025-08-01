package com.developerleetaehee.th_web_app.controller.board;

import com.developerleetaehee.th_web_app.domain.Board;
import com.developerleetaehee.th_web_app.dto.board.*;
import com.developerleetaehee.th_web_app.service.board.BoardService;
import com.developerleetaehee.th_web_app.utility.IpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/boards")
@Tag(name = "홈페이지 게시글 API", description = "홈페이지에 게시글 CRUD 처리를 위한 API")
public class BoardApiController {

    private final BoardService boardService;

    @Autowired
    private final BoardCustomConfig boardCustomConfig;

    @GetMapping
    @Operation(summary = "게시글 전체 조회", description = "조건과 페이징으로 조회합니다.")
    @Parameter(
            description = "게시글 종류 정보를 반드시 넘겨주세요.",
            schema = @Schema(allowableValues = {"notice : 공지사항, free : 자유게시판, adults_only : 성인전용, qna : 질의응답"})
    )
    public ResponseEntity<List<BoardResponse>> findAllBoards(
            @RequestParam(name = "start_page", defaultValue = "0") int startPage,
            @RequestParam(name = "per_page", defaultValue = "10") int perPage,
            @RequestParam(required = false) String writer,
            @RequestParam(required = false) String subject,
            @RequestParam(name = "board_type") String boardType
    ) {
        BoardInfo boardInfo = boardCustomConfig.getConfig().get(boardType);
        if (boardInfo == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 타입 없음");
        }

        // 검색을 위한 설정
        BoardSearchRequest boardSearchRequest = new BoardSearchRequest();
        boardSearchRequest.setPageRange(startPage, perPage);
        boardSearchRequest.setWriter(writer);
        boardSearchRequest.setSubject(subject);
        boardSearchRequest.setBoardType(boardType);

        List<BoardResponse> boards = boardService.findAll(boardSearchRequest)
                .stream()
                .map(BoardResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(boards);
    }

    @GetMapping("/{id}")
    @Operation(summary = "특정 게시글 조회", description = "고유정보로 게시글을 조회합니다.")
    public ResponseEntity<BoardResponse> findBoard(@PathVariable long id) {
        Board board = boardService.findById(id);

        return ResponseEntity.ok()
                .body(new BoardResponse(board));
    }

    @PostMapping
    @Operation(summary = "게시글 생성", description = "게시글을 신규로 생성합니다.")
    @Parameter(
            description = "게시글 종류 정보",
            schema = @Schema(
                    allowableValues = {"notice : 공지사항, free : 자유게시판, adults_only : 성인전용"}
            )
    )
    public ResponseEntity<BoardResponse> addBoard(
            @RequestBody AddBoardRequest request,
            HttpServletRequest httpRequest) {

        request.setIpAddress(IpUtil.getRealIp(httpRequest));

        Board savedBoard = boardService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BoardResponse(savedBoard));
    }

    @PutMapping("/{id}")
    @Operation(summary = "게시글 수정", description = "특정 게시글을 수정합니다.")
    public ResponseEntity<BoardResponse> updateBoard(
            @PathVariable long id,
            @RequestBody UpdateBoardRequest request) {

        Board updateBoard = boardService.update(id, request);

        return ResponseEntity.ok()
                .body(new BoardResponse(updateBoard));
    }

    @PatchMapping("/{id}/soft-delete")
    @Operation(summary = "게시글 소프트 삭제", description = "특정 데이터를 테이블에서 지우지 않고 플래그로 처리합니다.")
    public ResponseEntity<Void> softDeleteBoard(
            @PathVariable long id,
            @RequestBody DeleteBoardRequest request) {

        boardService.softDelete(id, request);

        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "게시글 삭제", description = "특정 게시글을 테이블에서 삭제한다.")
    public ResponseEntity<Void> deleteBoard(@PathVariable long id) {
        boardService.delete(id);

        return ResponseEntity.ok()
                .build();
    }
}
