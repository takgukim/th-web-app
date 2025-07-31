package com.developerleetaehee.th_web_app.dto.board;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Getter
public class BoardCustomConfig {
    private final Map<String, BoardInfo> config = Map.of(
            "notice", new BoardInfo("공지사항", "notice"),
            "free", new BoardInfo("자유게시판", "free"),
            "qna", new BoardInfo("질의응답", "qna"),
            "adults_only", new BoardInfo("성인전용", "adults_only")
    );
}
