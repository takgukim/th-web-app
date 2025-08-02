package com.developerleetaehee.th_web_app.dto.board;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Getter
public class BoardCustomConfig {
    private final Map<String, BoardInfo> config = Map.of(
            "notice", new BoardInfo(
                    "공지사항",
                    "티에이치쇼핑몰 공지사항 전해드려요.",
                    "공지사항",
                    "default",
                    false
            ),
            "free", new BoardInfo(
                    "자유게시판",
                    "고객의 소리를 자유롭게 남겨주세요.",
                    "자유게시판",
                    "default",
                    true
            ),
            "qna", new BoardInfo(
                    "질의응답",
                    "고객의 궁금한점에 대해서 답변해드리겠습니다.",
                    "질의응답",
                    "default",
                    true
            ),
            "adults_only", new BoardInfo(
                    "성인전용",
                    "성인전용 게시판입니다. 19세 미만 출입금지입니다.",
                    "성인전용",
                    "default",
                    true
            )
    );
}
