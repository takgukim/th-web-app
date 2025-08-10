package com.developerleetaehee.th_web_app.controller.board;

import com.developerleetaehee.th_web_app.domain.Counsel;
import com.developerleetaehee.th_web_app.dto.counsel.AddCounselRequest;
import com.developerleetaehee.th_web_app.dto.counsel.CounselResponse;
import com.developerleetaehee.th_web_app.service.board.CounselService;
import com.developerleetaehee.th_web_app.utility.IpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/counsels")
@Tag(name = "홈페이지 및 관리자 상담 메뉴 API", description = "홈페이지에서 사용자와 관리자페이지 상담 메뉴 API")
public class CounselApiController {

    private final CounselService counselService;

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
            request.setIpAddress(IpUtil.getRealIp(httpRequest));
            request.setProgressState("pending");

            Counsel saveCounsel = counselService.save(request);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new CounselResponse(saveCounsel));
    }
}
