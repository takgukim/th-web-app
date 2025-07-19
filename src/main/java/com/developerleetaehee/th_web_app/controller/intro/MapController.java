package com.developerleetaehee.th_web_app.controller.intro;

import com.developerleetaehee.th_web_app.config.NaverMapProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/intro")
public class MapController {

    private final NaverMapProperties naverMapProperties;

    @GetMapping("/map")
    public String test(Model model) {
        model.addAttribute("title", "찾아오는 길");
        model.addAttribute("mapClientId", naverMapProperties.getClientId());

        return "intro/naver_map";
    }
}
