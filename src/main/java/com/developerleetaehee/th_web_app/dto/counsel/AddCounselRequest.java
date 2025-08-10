package com.developerleetaehee.th_web_app.dto.counsel;

import com.developerleetaehee.th_web_app.domain.Counsel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddCounselRequest {
    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("counsel_method")
    private String counselMethod;

    private String phone;

    private String email;

    @JsonProperty("counsel_kind")
    private String counselKind;

    @JsonProperty("progress_state")
    private String progressState;

    @JsonProperty("request_memo")
    private String requestMemo;

    private String ipAddress;

    @JsonProperty("apply_date")
    private String applyDate;

    public Counsel toEntity() {
        return Counsel.builder()
                .customerName(customerName)
                .counselMethod(counselMethod)
                .phone(phone)
                .email(email)
                .counselKind(counselKind)
                .progressState(progressState)
                .requestMemo(requestMemo)
                .ipAddress(ipAddress)
                .build();
    }
}
