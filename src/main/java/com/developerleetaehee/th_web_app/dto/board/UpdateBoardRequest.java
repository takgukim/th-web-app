package com.developerleetaehee.th_web_app.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateBoardRequest {
    private String writer;
    private String subject;
    private String content;
    private String updateUser;
}
