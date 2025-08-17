package com.developerleetaehee.th_web_app.controller.intro;

import com.developerleetaehee.th_web_app.common.MenuCustomCode;
import com.developerleetaehee.th_web_app.common.MenuInfo;
import com.developerleetaehee.th_web_app.config.NaverMapProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/intro")
public class MapController {

    private final MenuCustomCode menuCustomCode;

    private final NaverMapProperties naverMapProperties;

    @GetMapping("/map")
    public String naverMap(Model model) {

        MenuInfo menuInfo = menuCustomCode.getCode().get("naver_map");

        if (menuInfo == null) {
            throw new IllegalArgumentException("존재하지 않는 게시판 유형입니다. 관리자에게 문의하세요.");
        }

        model.addAttribute("title", menuInfo.getTitle());
        model.addAttribute("menuCustomCode", menuInfo);
        model.addAttribute("mapClientId", naverMapProperties.getClientId());

        return "intro/naver_map";
    }
}
