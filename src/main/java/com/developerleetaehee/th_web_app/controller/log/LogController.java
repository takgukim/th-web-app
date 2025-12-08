package com.developerleetaehee.th_web_app.controller.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/log")
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @GetMapping("/logger")
    public String loggerTest() {

        logger.info("깃허브에 업로드해놓고 도커 테스트 ㅋ");

        return "깃허브에 업로드해놓고 도커 테스트 ㅋ";
    }
}
