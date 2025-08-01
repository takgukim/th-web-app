package com.developerleetaehee.th_web_app.dto.board;

import com.developerleetaehee.th_web_app.domain.Board;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardViewResponse {
    private final long idx;
    private final String writer;
    private final String subject;
    private final String content;
    private final String boardType;
    private final int readCount;
    private final LocalDateTime registerDatetime;

    public BoardViewResponse(Board board) {
        this.idx = board.getIdx();
        this.writer = board.getWriter();
        this.subject = board.getSubject();
        this.content = board.getContent();
        this.boardType = board.getBoardType();
        this.readCount = board.getReadCount();
        this.registerDatetime = board.getRegisterDatetime();
    }
}
