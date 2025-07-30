package com.developerleetaehee.th_web_app.dto.board;


import com.developerleetaehee.th_web_app.domain.Board;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class BoardViewResponse {
    private final long idx;
    private final String writer;
    private final String subject;
    private final String content;
    private final String writeDate;
    private final int readCount;
    private final LocalDateTime registerDatetime;

    public BoardViewResponse(Board board) {
        LocalDate fmtDate = LocalDate.parse(board.getWriteDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        this.idx = board.getIdx();
        this.writer = board.getWriter();
        this.subject = board.getSubject();
        this.content = board.getContent();
        this.readCount = board.getReadCount();
        this.registerDatetime = board.getRegisterDatetime();

        this.writeDate = fmtDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}
