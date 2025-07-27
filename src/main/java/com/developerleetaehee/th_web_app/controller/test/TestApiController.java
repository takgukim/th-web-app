package com.developerleetaehee.th_web_app.controller.test;

import com.developerleetaehee.th_web_app.domain.Board;
import com.developerleetaehee.th_web_app.dto.board.BoardResponse;
import com.developerleetaehee.th_web_app.dto.board.BoardSearchRequest;
import com.developerleetaehee.th_web_app.service.test.TestService;
import com.developerleetaehee.th_web_app.utility.IpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tests")
@Tag(name = "mybatis 샘플 예제 API", description = "mybatis 샘플 CRUD 소스 참고 바랍니다.")
public class TestApiController {
    private final TestService testService;

    private static final Logger log = LoggerFactory.getLogger(TestApiController.class);

    @GetMapping
    @Operation(summary = "게시글 전체 조회", description = "조건과 페이징으로 조회합니다.")
    @Parameter(
            description = "게시글 종류 정보를 반드시 넘겨주세요.",
            schema = @Schema(
                    allowableValues = {"notice : 공지사항, free : 자유게시판, adults_only : 성인전용"}
            )
    )
    public ResponseEntity<List<BoardResponse>> findAllBoard(
            @RequestParam(name = "start_page", defaultValue = "0") int startPage,
            @RequestParam(name = "per_page", defaultValue = "10") int perPage,
            @RequestParam(required = false) String writer,
            @RequestParam(required = false) String subject,
            @RequestParam(name = "board_type") String boardType
    ) {

        log.info("로깅테스트");

        // 검색을 위한 설정
        BoardSearchRequest boardSearchRequest = new BoardSearchRequest();
        boardSearchRequest.setPageRange(startPage, perPage);
        boardSearchRequest.setWriter(writer);
        boardSearchRequest.setSubject(subject);
        boardSearchRequest.setBoardType(boardType);

        List<BoardResponse> boards =  testService.findAll(boardSearchRequest)
                .stream()
                .map(BoardResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(boards);
    }

    @GetMapping("/{id}")
    @Operation(summary = "특정 게시글 조회", description = "고유정보로 게시글을 조회합니다.")
    public ResponseEntity<BoardResponse> getBoard(@PathVariable long id) {
        Board board = testService.findById(id);

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
            @RequestBody Board request,
            HttpServletRequest httpRequest) {

        request.setIpAddress(IpUtil.getRealIp(httpRequest));

        Board board = testService.save(request);

        return ResponseEntity.ok()
                .body(new BoardResponse(board));
    }

    @PutMapping("/{id}")
    @Operation(summary = "게시글 수정", description = "특정 게시글을 수정합니다.")
    public ResponseEntity<BoardResponse> updateBoard(
            @PathVariable Long id,
            @RequestBody Board request) {

        System.out.println("컨트롤러 가져오나 ?? " + request.getReadCount());

        Board board = testService.update(id, request);

        return ResponseEntity.ok()
                .body(new BoardResponse(board));
    }

    @PatchMapping("/{id}/soft-delete")
    @Operation(summary = "게시글 소프트 삭제", description = "특정 데이터를 테이블에서 지우지 않고 플래그로 처리합니다.")
    public ResponseEntity<Void> softDeleteBoard(
            @PathVariable long id,
            @RequestBody Board request) {

        testService.softDelete(id, request);

        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "게시글 삭제", description = "특정 게시글을 테이블에서 삭제한다.")
    public ResponseEntity<Void> deleteBoard(@PathVariable long id) {
        testService.delete(id);

        return ResponseEntity.ok()
                .build();
    }
}
