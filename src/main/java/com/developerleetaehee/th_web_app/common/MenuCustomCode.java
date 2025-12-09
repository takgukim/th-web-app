package com.developerleetaehee.th_web_app.common;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Getter
public class MenuCustomCode {
    private final Map<String, MenuInfo> code = Map.ofEntries(
       Map.entry("company_intro", new MenuInfo("회사소개", "티에이치 쇼핑몰에 대해서 소개해드려요.")),
       Map.entry("naver_map", new MenuInfo("찾아오는 길", "티에이치 쇼핑몰 찾아오는 길 안내해드려요.")),
       Map.entry("product_list", new MenuInfo("상품목록","티에이치 쇼핑몰 제품 소개해드려요.")),
       Map.entry("counsel_list", new  MenuInfo("상담 진행 현황", "고객님의 상담 신청 현황을 확인하세요.")),
       Map.entry("counsel_write", new MenuInfo("상담신청", "상담신청 하시면 상담사가 답변드려요.")),
       Map.entry("sign_write", new MenuInfo("회원가입", "티에이치스터디 회원이 되시는 것을 환영합니다.")),
       Map.entry("notice", new MenuInfo("공지사항", "티에이치쇼핑몰 공지사항 전해드려요.")),
       Map.entry("free", new MenuInfo("자유게시판", "고객의 소리를 자유롭게 남겨주세요.")),
       Map.entry("qna", new MenuInfo("질의응답", "고객의 궁금한점에 대해서 답변해드리겠습니다.")),
       Map.entry("adults_only", new MenuInfo("성인전용", "성인전용 게시판입니다. 19세 미만 출입금지입니다.")),
       Map.entry("log_logger_test", new MenuInfo("로그 로거 테스트", "운영, 도커, 개발 로그 확인 메뉴"))
    );
}
