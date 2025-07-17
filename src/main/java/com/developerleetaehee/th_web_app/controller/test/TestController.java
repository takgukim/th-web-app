package com.developerleetaehee.th_web_app.controller.test;

import com.developerleetaehee.th_web_app.service.test.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class TestController {

    private final TestService testService;

    @GetMapping("/test_layout")
    public String test(Model model) {
        model.addAttribute("title", "홈");
        return "layouts/test/test_layout";
    }

    @GetMapping("/test_dialect")
    public String testDialect(Model model) {
        model.addAttribute("title", "홈");
        return "test/dialect";
    }
}
