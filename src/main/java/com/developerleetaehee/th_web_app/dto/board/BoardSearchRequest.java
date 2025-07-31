package com.developerleetaehee.th_web_app.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public void setSearchStartDate(String writeDate) {
        LocalDate parsedDate = LocalDate.parse(writeDate);
        this.searchStartDate = parsedDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public void setSearchEndDate(String writeDate) {
        LocalDate parsedDate = LocalDate.parse(writeDate);
        this.searchEndDate = parsedDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

}
