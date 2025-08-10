package com.developerleetaehee.th_web_app.dto.counsel;

import com.developerleetaehee.th_web_app.domain.Counsel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Schema(description = "상담 응답 DTO")
public class CounselResponse {
    private final long idx;
    private final String customerName;
    private final String counselMethod;
    private final String phone;
    private final String email;
    private final String counselKind;
    private final String requestMemo;
    private final String applyDate;

    public CounselResponse(Counsel counsel) {

        LocalDate fmtDate = LocalDate.parse(counsel.getApplyDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        this.idx = counsel.getIdx();
        this.customerName = counsel.getCustomerName();
        this.counselMethod = counsel.getCounselMethod();
        this.phone = counsel.getPhone();
        this.email = counsel.getEmail();
        this.counselKind = counsel.getCounselKind();
        this.requestMemo = counsel.getRequestMemo();
        this.applyDate = fmtDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

}
