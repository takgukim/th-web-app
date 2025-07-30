package com.developerleetaehee.th_web_app.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardSearchRequest {
    private int startPage;
    private int perPage;
    private String writer;
    private String subject;
    private String boardType;
    private String writeDate;
    private String searchStartDate;
    private String searchEndDate;

    public void setPageRange(int startPage, int perPage) {
        this.startPage = startPage;
        this.perPage = perPage;
    }
}
