package com.developerleetaehee.th_web_app.dto.counsel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DeleteCounselRequest {
    @JsonProperty("delete_user")
    private String deleteUser;
}
