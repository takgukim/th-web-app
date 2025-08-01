package com.developerleetaehee.th_web_app.dto.board;

import com.developerleetaehee.th_web_app.domain.Board;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddBoardRequest {
    private String writer;

    private String subject;

    private String content;

    private String writeDate;

    @JsonProperty("board_type")
    private String boardType;

    private String ipAddress;

    public Board toEntity() {
        return Board.builder()
                .writer(writer)
                .subject(subject)
                .content(content)
                .ipAddress(ipAddress)
                .boardType(boardType)
                .build();
    }
}