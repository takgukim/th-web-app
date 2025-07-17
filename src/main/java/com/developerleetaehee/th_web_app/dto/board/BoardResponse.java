package com.developerleetaehee.th_web_app.dto.board;

import com.developerleetaehee.th_web_app.domain.Board;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public class BoardResponse {
    private final Long idx;
    private final String writer;
    private final String subject;
    private final String content;
    private final String writeDate;

    public BoardResponse(Board board) {

        LocalDate fmtDate = LocalDate.parse(board.getWriteDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        this.idx = board.getIdx();
        this.writer = board.getWriter();
        this.subject = board.getSubject();
        this.content = board.getContent();
        this.writeDate = fmtDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}
