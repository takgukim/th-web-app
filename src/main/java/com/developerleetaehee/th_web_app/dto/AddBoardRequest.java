package com.developerleetaehee.th_web_app.dto;

import com.developerleetaehee.th_web_app.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddBoardRequest {
    private String writer;
    private String subject;
    private String content;
    private String writeDate;

    public Board toEntity() {
        return Board.builder()
                .writer(writer)
                .subject(subject)
                .content(content)
                .build();
    }
}