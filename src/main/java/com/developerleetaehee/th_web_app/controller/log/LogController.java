package com.developerleetaehee.th_web_app.controller.log;

import com.developerleetaehee.th_web_app.common.MenuCustomCode;
import com.developerleetaehee.th_web_app.common.MenuInfo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/log")
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    private final MenuCustomCode menuCustomCode;

    @GetMapping("/logger")
    public String loggerTest(Model model) {

        logger.info("도커 로그 테스트 100");
        logger.info("도커 로크 테스트 200");
        logger.info("도커 로크 테스트 300");

        MenuInfo menuInfo = menuCustomCode.getCode().get("log_logger_test");

        if (menuInfo == null) {
            throw new IllegalArgumentException("존재하지 않는 게시판 유형입니다. 관리자에게 문의하세요.");
        }

        model.addAttribute("title", menuInfo.getTitle());
        model.addAttribute("menuCustomCode", menuInfo);

        return "shop/product_list";
    }
}
