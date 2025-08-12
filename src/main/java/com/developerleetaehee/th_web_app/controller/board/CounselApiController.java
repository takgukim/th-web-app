package com.developerleetaehee.th_web_app.controller.board;

import com.developerleetaehee.th_web_app.domain.Counsel;
import com.developerleetaehee.th_web_app.dto.counsel.*;
import com.developerleetaehee.th_web_app.service.board.CounselService;
import com.developerleetaehee.th_web_app.utility.IpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/counsels")
@Tag(name = "홈페이지 및 관리자 상담 메뉴 API", description = "홈페이지에서 사용자와 관리자페이지 상담 메뉴 API")
public class CounselApiController {

    private final CounselService counselService;

    @Autowired
    private final CounselCustomCode counselCustomCode;

    @GetMapping
    @Operation(summary = "상담 전체 조회", description = "조건과 페이징으로 조회합니다. 기본값은 상담진행상태 대기")
    @ApiResponse(
            responseCode = "200",
            description = "상담 목록 DTO 객체"
    )
    public ResponseEntity<List<CounselResponse>> findAllCounsels(
            @RequestParam(name = "start_page", defaultValue = "0") int startPage,
            @RequestParam(name = "per_page", defaultValue = "10") int perPage,
            @RequestParam(name = "customer_name", defaultValue = "") String customerName,
            @RequestParam(name = "counsel_method", defaultValue = "") String counselMethod,
            @RequestParam(name = "counsel_kind", defaultValue = "") String counselKind,
            @RequestParam(name = "progress_state", defaultValue = "pending") String progressStat,
            @RequestParam(name = "start_date", defaultValue = "") String startDate,
            @RequestParam(name = "end_date", defaultValue = "") String endDate
    ) {
        if (startDate == null || startDate.isBlank()) {
            startDate = LocalDate.now().minusMonths(1).toString();
        }
        if (endDate == null || endDate.isBlank()) {
            endDate = LocalDate.now().toString();
        }

        CounselSearchRequest counselSearchRequest = new CounselSearchRequest();
        counselSearchRequest.setPageRange(startPage, perPage);
        counselSearchRequest.setCustomerName(customerName);
        counselSearchRequest.setCounselMethod(counselMethod);
        counselSearchRequest.setCounselKind(counselKind);
        counselSearchRequest.setProgressState(progressStat);
        counselSearchRequest.setSearchStartDate(startDate);
        counselSearchRequest.setSearchEndDate(endDate);

        List<CounselResponse> boards = counselService.findAll(counselSearchRequest)
                .stream()
                .map(entity -> new CounselResponse(entity, counselCustomCode))
                .toList();

        return ResponseEntity.ok()
                .body(boards);
    }

    @GetMapping("/{id}")
    @Operation(summary = "특정 상담내용 조회", description = "고유정보로 고객 상담글을 조회합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "상담 목록 DTO"
    )
    public ResponseEntity<CounselResponse> findCounsel(@PathVariable long id) {
        Counsel counsel = counselService.findById(id);

        return ResponseEntity.ok()
                .body(new CounselResponse(counsel, counselCustomCode));
    }

    @PostMapping
    @Operation(summary = "상담 신청", description = "사용자가 상담을 신청한다")
    @ApiResponse(
            responseCode = "201",
            description = "상담 엔티티",
            content = @Content(schema = @Schema(implementation = Counsel.class))
    )
    public ResponseEntity<CounselResponse> addCounsel(
            @RequestBody AddCounselRequest request,
            HttpServletRequest httpRequest
    ) {
        String counselMethod = request.getCounselMethod();
        String counselKind = request.getCounselKind();

        String chkCounselMethod = this.getCodeValidate("counsel_method", counselMethod);
        if (chkCounselMethod == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "상담 방법이 유효하지 않습니다.");
        }

        String chkCounselKind = this.getCodeValidate("counsel_kind", counselKind);
        if (chkCounselKind == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "상담 종류가 유효하지 않습니다.");
        }

        request.setIpAddress(IpUtil.getRealIp(httpRequest));
        request.setProgressState("pending");
        Counsel saveCounsel = counselService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CounselResponse(saveCounsel, counselCustomCode));
    }

    @PutMapping("/{id}")
    @Operation(summary = "고객 상담글 수정", description = "관리자가 고객의 상담글에 조언 및 답변을 합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "상담 목록 DTO"
    )
    public ResponseEntity<CounselResponse> updateCounsel(
            @PathVariable long id,
            @RequestBody UpdateCounselRequest request
    ) {
        Counsel updateCounsel = counselService.update(id, request);

        return ResponseEntity.ok()
                .body(new CounselResponse(updateCounsel, counselCustomCode));
    }

    @PatchMapping("/{id}/state")
    @Operation(summary = "고객의 상담 진행 상태를 변경", description = "관리자가 상담글 진행상태를 변경한다")
    @ApiResponse(
            responseCode = "200",
            description = "상담 목록 DTO"
    )
    public ResponseEntity<CounselResponse> updateCounselState(
            @PathVariable long id,
            @RequestBody UpdateCounselState request
    ) {
        String progressState = request.getProgressState();
        System.out.println("progress_state = " + progressState);

        String chkProgressState = this.getCodeValidate("progress_state", progressState);
        if (chkProgressState == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "상담 진행 상태가 유효하지 않습니다.");
        }

        Counsel updateCounsel = counselService.updateState(id, request);

        return ResponseEntity.ok()
                .body(new CounselResponse(updateCounsel, counselCustomCode));
    }

    @PatchMapping("/{id}/soft-delete")
    @Operation(summary = "상담글 소프트 삭제", description = "특정 데이터를 테이블에서 지우지 않고 플래그로 처리합니다.")
    public ResponseEntity<Map<String, String>> softDeleteCounsel(
            @PathVariable long id,
            @RequestBody DeleteCounselRequest request) {

        counselService.softDelete(id, request);

        return ResponseEntity.ok(Map.of("message", "soft delete success"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "상담글 삭제", description = "특정 상담글을 테이블에서 삭제한다.")
    @ApiResponse(
            responseCode = "200"
    )
    public ResponseEntity<Void> deleteCounsel(@PathVariable long id) {
        counselService.deleteById(id);

        return ResponseEntity.ok()
                .build();
    }

    /**
     * 상담 코드 검증 (유효성 클래스로 교체 검토 필요)
     *
     * @param String code
     * @param String value
     *
     * @return String
     */
    private String getCodeValidate(String code, String value) {
        String result = null;

        switch (code) {
            case "counsel_method" :
            case "counsel_kind" :
            case "progress_state" :
                result = counselService.checkCounselCode(code, value);
                break;
        }

        return result;
    }
}
