package com.developerleetaehee.th_web_app.utility;

import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

public class PaginationUtil {
    public static Map<String, Object> buildPage(Page<?> page, String pageUrl, int startPage, int perPage) {
        long boardTotal = page.getTotalElements();
        long pagingStartNo = boardTotal - (perPage * (startPage));

        int currentPage = page.getNumber() + 1;
        int totalPage = page.getTotalPages();

        // 페이징 UI에 보여줄 페이지 번호 리스트 생성 (현재 페이지 기준 +-2, 최대 5개)
        int startRangePage = Math.max(1, currentPage - 2);
        int endRangePage = Math.min(totalPage, startPage + 4);

        if (endRangePage - startRangePage < 4) {
            // startPage가 끝 페이지와 맞춰서 5개가 안되면 startPage 조정
            startRangePage = Math.max(1, endRangePage - 4);
        }

        Map<String, Object> pages = new HashMap<>();
        pages.put("pagingStartNo", pagingStartNo);
        pages.put("pageUrl", pageUrl);
        pages.put("currentPage", currentPage);
        pages.put("totalPage", totalPage);
        pages.put("startRangePage", startRangePage);
        pages.put("endRangePage", endRangePage);

        System.out.println("current page = " + currentPage);

        return pages;
    }
}
