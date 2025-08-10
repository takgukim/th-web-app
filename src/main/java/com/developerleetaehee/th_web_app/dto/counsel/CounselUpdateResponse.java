package com.developerleetaehee.th_web_app.dto.counsel;

import com.developerleetaehee.th_web_app.domain.Counsel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Schema(description = "관리자 상담 데이터 응답 DTO")
public class CounselUpdateResponse {
    private final long idx;
    private final String customerName;
    private final String counselMethod;
    private final String counselContent;
    private final String counselKind;
    private final String progressState;
    private final String requestMemo;
    private final String companyMemo;
    private final String applyDate;

    public CounselUpdateResponse(Counsel counsel, CounselCustomCode codeMap) {

        LocalDate fmtDate = LocalDate.parse(counsel.getApplyDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        this.idx = counsel.getIdx();
        this.customerName = counsel.getCustomerName();
        this.counselMethod = codeMap.getCounselMethodName(counsel.getCounselMethod());
        this.counselContent = counsel.getCounselContent();
        this.counselKind = codeMap.getCounselKindName(counsel.getCounselKind());
        this.progressState = codeMap.getCounselStateName(counsel.getProgressState());
        this.requestMemo = counsel.getRequestMemo();
        this.companyMemo = counsel.getCompanyMemo();
        this.applyDate = fmtDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}
