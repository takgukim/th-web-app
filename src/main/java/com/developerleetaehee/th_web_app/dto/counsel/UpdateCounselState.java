package com.developerleetaehee.th_web_app.dto.counsel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateCounselState {
    @JsonProperty("progress_state")
    private String progressState;

    @JsonProperty("update_user")
    private String updateUser;
}
