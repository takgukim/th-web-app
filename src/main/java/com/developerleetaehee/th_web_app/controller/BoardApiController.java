package com.developerleetaehee.th_web_app.controller;

import com.developerleetaehee.th_web_app.domain.Board;
import com.developerleetaehee.th_web_app.dto.AddBoardRequest;
import com.developerleetaehee.th_web_app.dto.BoardResponse;
import com.developerleetaehee.th_web_app.dto.DeleteBoardRequest;
import com.developerleetaehee.th_web_app.dto.UpdateBoardRequest;
import com.developerleetaehee.th_web_app.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/boards")
public class BoardApiController {
    private final BoardService boardService;

    @GetMapping
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
    public ResponseEntity<BoardResponse> findBoard(@PathVariable long id) {
        Board board = boardService.findById(id);

        return ResponseEntity.ok()
                .body(new BoardResponse(board));
    }

    @PostMapping
    public ResponseEntity<BoardResponse> addBoard(
            @RequestBody AddBoardRequest request,
            HttpServletRequest httpRequest) {

        request.setIpAddress(this.getRealIp(httpRequest));

        Board savedBoard = boardService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BoardResponse(savedBoard));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardResponse> updateBoard(
            @PathVariable long id,
            @RequestBody UpdateBoardRequest request) {

        Board updateBoard = boardService.update(id, request);

        return ResponseEntity.ok()
                .body(new BoardResponse(updateBoard));
    }

    @PatchMapping("/{id}/soft-delete")
    public ResponseEntity<Void> softDeleteBoard(
            @PathVariable long id,
            @RequestBody DeleteBoardRequest request) {

        boardService.softDelete(id, request);

        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable long id) {
        boardService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    private String getRealIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
            return ip.split(",")[0];
        }
        ip = request.getHeader("Proxy-Client-IP");
        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("WL-Proxy-Client-IP");
        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }
}
