package com.developerleetaehee.th_web_app.dto.board;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DeleteBoardRequest {

    @JsonProperty("delete_user")
    private String deleteUser;
}
