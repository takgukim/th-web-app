package com.developerleetaehee.th_web_app.controller.board;

import com.developerleetaehee.th_web_app.controller.HomeController;
import com.developerleetaehee.th_web_app.domain.Board;
import com.developerleetaehee.th_web_app.dto.board.AddBoardRequest;
import com.developerleetaehee.th_web_app.dto.board.BoardResponse;
import com.developerleetaehee.th_web_app.dto.board.DeleteBoardRequest;
import com.developerleetaehee.th_web_app.dto.board.UpdateBoardRequest;
import com.developerleetaehee.th_web_app.service.boarf.BoardService;
import com.developerleetaehee.th_web_app.utility.IpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/boards")
@Tag(name = "홈페이지 게시글 API", description = "홈페이지에 게시글 CRUD 처리를 위한 API")
public class BoardApiController {
    private final BoardService boardService;

    private static final Logger log = LoggerFactory.getLogger(BoardApiController.class);

    @GetMapping
    @Operation(summary = "게시글 전체 조회", description = "조건과 페이징으로 조회합니다.")
    public ResponseEntity<List<BoardResponse>> findAllBoards(
            @RequestParam(name = "start_page", defaultValue = "0") int startPage,
            @RequestParam(name = "per_page", defaultValue = "10") int perPage
    ) {
        List<BoardResponse> boards = boardService.findAll(startPage, perPage)
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
