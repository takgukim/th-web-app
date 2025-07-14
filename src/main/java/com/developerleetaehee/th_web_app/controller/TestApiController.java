package com.developerleetaehee.th_web_app.controller;

import com.developerleetaehee.th_web_app.domain.Board;
import com.developerleetaehee.th_web_app.dto.BoardResponse;
import com.developerleetaehee.th_web_app.service.TestService;
import com.developerleetaehee.th_web_app.utility.IpUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tests")
public class TestApiController {
    private final TestService testService;

    @GetMapping
    public ResponseEntity<List<BoardResponse>> findAllBoard(
            @RequestParam(name = "start_page", defaultValue = "0") int startPage,
            @RequestParam(name = "per_page", defaultValue = "10") int perPage
    ) {
        List<BoardResponse> boards =  testService.findAll(startPage, perPage)
                .stream()
                .map(BoardResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(boards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> getBoard(@PathVariable long id) {
        Board board = testService.findById(id);

        return ResponseEntity.ok()
                .body(new BoardResponse(board));
    }

    @PostMapping
    public ResponseEntity<BoardResponse> addBoard(
            @RequestBody Board request,
            HttpServletRequest httpRequest) {

        request.setIpAddress(IpUtil.getRealIp(httpRequest));

        Board board = testService.save(request);

        return ResponseEntity.ok()
                .body(new BoardResponse(board));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardResponse> updateBoard(
            @PathVariable Long id,
            @RequestBody Board request) {

        Board board = testService.update(id, request);

        return ResponseEntity.ok()
                .body(new BoardResponse(board));
    }

    @PatchMapping("/{id}/soft-delete")
    public ResponseEntity<Void> softDeleteBoard(
            @PathVariable long id,
            @RequestBody Board request) {

        testService.softDelete(id, request);

        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable long id) {
        testService.delete(id);

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
