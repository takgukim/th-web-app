package com.developerleetaehee.th_web_app.utility;

import org.springframework.data.domain.Page;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PaginationUtil {
    public static Map<String, Object> buildPage(Page<?> page, int startPage, int perPage, String pageUrl, String queryString) {
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

        String qs = "";
        if (queryString != null && !queryString.isEmpty()) {
            qs = String.format("&%s", queryString);
        }

        Map<String, Object> pages = new HashMap<>();
        pages.put("pagingStartNo", pagingStartNo);
        pages.put("pageUrl", pageUrl);
        pages.put("currentPage", currentPage);
        pages.put("totalPage", totalPage);
        pages.put("startRangePage", startRangePage);
        pages.put("endRangePage", endRangePage);
        pages.put("queryString", qs);

        return pages;
    }

    public static String buildQueryStringFromMap(Map<String, ?> map) {
        return map.entrySet().stream()
                .filter(e -> e.getValue() != null && !e.getValue().toString().isBlank())
                .map(e -> URLEncoder.encode(e.getKey(), StandardCharsets.UTF_8) + "=" +
                        URLEncoder.encode(e.getValue().toString(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));
    }
}
