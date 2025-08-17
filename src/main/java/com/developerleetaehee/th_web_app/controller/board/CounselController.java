package com.developerleetaehee.th_web_app.controller.board;

import com.developerleetaehee.th_web_app.common.MenuCustomCode;
import com.developerleetaehee.th_web_app.common.MenuInfo;
import com.developerleetaehee.th_web_app.domain.Counsel;
import com.developerleetaehee.th_web_app.dto.counsel.CounselCustomCode;
import com.developerleetaehee.th_web_app.dto.counsel.CounselListViewResponse;
import com.developerleetaehee.th_web_app.dto.counsel.CounselSearchRequest;

import com.developerleetaehee.th_web_app.service.board.CounselService;
import com.developerleetaehee.th_web_app.utility.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/counsel")
public class CounselController {

    private final MenuCustomCode menuCustomCode;

    private final CounselService counselService;

    @Autowired
    private CounselCustomCode counselCustomCode;

    @GetMapping("/write")
    public String writeForm(Model model) {

        MenuInfo menuInfo = menuCustomCode.getCode().get("counsel_write");

        if (menuInfo == null) {
            throw new IllegalArgumentException("존재하지 않는 게시판 유형입니다. 관리자에게 문의하세요.");
        }

        model.addAttribute("title", menuInfo.getTitle());
        model.addAttribute("menuCustomCode", menuInfo);
        model.addAttribute("counselMethodMap", counselCustomCode.getCounselMethodMap());
        model.addAttribute("counselKindMap", counselCustomCode.getCounselKindMap());

        return "board/counsel_write";
    }

    @GetMapping
    public String findAllBoards(
        @RequestParam(name = "start_page", defaultValue = "0") int startPage,
        @RequestParam(name = "per_page", defaultValue = "10") int perPage,
        @RequestParam(name = "search_start_date", defaultValue = "") String searchStartDate,
        @RequestParam(name = "search_end_date", defaultValue = "") String searchEndDate,
        @RequestParam(name = "search_customer_name", defaultValue = "") String searchCustomerName,
        Model model) {

        MenuInfo menuInfo = menuCustomCode.getCode().get("counsel_list");

        if (menuInfo == null) {
            throw new IllegalArgumentException("존재하지 않는 게시판 유형입니다. 관리자에게 문의하세요.");
        }

        if (searchStartDate == null || searchStartDate.isBlank()) {
            searchStartDate = LocalDate.now().minusMonths(1).toString();
        }
        if (searchEndDate == null || searchEndDate.isBlank()) {
            searchEndDate = LocalDate.now().toString();
        }

        CounselSearchRequest counselSearchRequest = new CounselSearchRequest();
        counselSearchRequest.setPageRange(startPage, perPage);
        counselSearchRequest.setSearchStartDate(searchStartDate);
        counselSearchRequest.setSearchEndDate(searchEndDate);
        counselSearchRequest.setCustomerName(searchCustomerName);

        Map<String, Object> searchParamMap = new HashMap<>();
        searchParamMap.put("search_start_date", "");
        searchParamMap.put("search_end_date", "");
        searchParamMap.put("search_customer_name", "");

        if (StringUtils.hasText(searchStartDate)) {
            searchParamMap.put("search_start_date", searchStartDate);
        }

        if (StringUtils.hasText(searchEndDate)) {
            searchParamMap.put("search_end_date", searchEndDate);
        }

        if (StringUtils.hasText(searchCustomerName)) {
            searchParamMap.put("search_customer_name", searchCustomerName);
        }

        List<CounselListViewResponse> counsels = counselService.findAll(counselSearchRequest)
                .stream()
                .map(entity -> new CounselListViewResponse(entity, counselCustomCode))
                .toList();

        // 페이징 설정
        String pageUrl = "counsel";
        Page<Counsel> counselPage = counselService.getCounselPage(counselSearchRequest);
        String queryString = PaginationUtil.buildQueryStringFromMap(searchParamMap);
        Map<String, Object> pages = PaginationUtil.buildPage(counselPage, startPage, perPage, pageUrl, queryString);

        model.addAttribute("title", menuInfo.getTitle());
        model.addAttribute("menuCustomCode", menuInfo);
        model.addAttribute("counsels", counsels);
        model.addAttribute("pages", pages);
        model.addAttribute("searchParamMap", searchParamMap);

        return "board/counsel_list";
    }
}
