package com.developerleetaehee.th_web_app.dto.board;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Getter
public class BoardCustomCode {
    private final Map<String, BoardInfo> code = Map.of(
            "notice", new BoardInfo(
                    "공지사항게시판",
                    "공지사항",
                    "default",
                    false
            ),
            "free", new BoardInfo(
                    "자유게시판",
                    "자유게시판",
                    "default",
                    true
            ),
            "qna", new BoardInfo(
                    "질의응답게시판",
                    "질의응답",
                    "default",
                    true
            ),
            "adults_only", new BoardInfo(
                    "성인전용게시판",
                    "성인전용",
                    "default",
                    true
            )
    );

    public String getCodeName(String code) {

        BoardInfo boardInfo = this.getCode().get(code);

        String newCodeName = "unknown";
        if (boardInfo != null) {
            newCodeName = boardInfo.getCodeName();
        }

        return newCodeName;
    }
}
