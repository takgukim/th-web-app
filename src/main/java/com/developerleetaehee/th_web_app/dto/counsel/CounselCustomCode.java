package com.developerleetaehee.th_web_app.dto.counsel;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Getter
public class CounselCustomCode {
    private final Map<String, String> counselMethodMap = new LinkedHashMap<>() {{
        put("call", "전화");
        put("email", "이메일");
    }};

    private final Map<String, String> counselKindMap = new LinkedHashMap<>() {{
        put("compliment", "칭찬");
        put("complaint", "불만");
        put("refund", "환불");
        put("exchange", "교환");
        put("suggestion", "건의");
        put("vendor_apply", "입점");
    }};

    private final Map<String, String> counselStateMap = new LinkedHashMap<>() {{
        put("pending", "대기");
        put("in_progress", "진행중");
        put("completed", "완료");
        put("on_hold", "보류");
    }};

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
