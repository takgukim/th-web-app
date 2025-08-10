package com.developerleetaehee.th_web_app.dto.counsel;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CounselCustomCode {
    private final Map<String, String> counselMethodMap = Map.of(
            "call", "전화",
            "email", "이메일"
    );

    private final Map<String, String> counselKindMap = Map.of(
            "compliment", "칭찬",
            "complaint", "불만",
            "refund", "환불",
            "exchange", "교환",
            "suggestion", "건의",
            "vendor_apply", "입점"

    );

    private final Map<String, String> counselStateMap = Map.of(
            "pending", "대기",
            "in_progress", "진행중",
            "completed", "완료",
            "on_hold", "보류"
    );

    public String getCounselMethodName(String code) {
        return this.counselMethodMap.getOrDefault(code, "unknown");
    }

    public String getCounselKindName(String code) {
        return this.counselKindMap.getOrDefault(code, "unknown");
    }

    public String getCounselStateName(String code) {
        return this.counselStateMap.getOrDefault(code, "unknown");
    }

}
