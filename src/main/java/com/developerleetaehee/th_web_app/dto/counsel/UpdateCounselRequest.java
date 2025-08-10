package com.developerleetaehee.th_web_app.dto.counsel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateCounselRequest {
    @JsonProperty("company_memo")
    private String companyMemo;

    @JsonProperty("update_user")
    private String updateUser;
}
