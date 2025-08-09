package com.developerleetaehee.th_web_app.controller.api;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/csrf-token")
@Tag(name = "CSRF Token", description = "CSRF Token과 관련된 API")
public class CsrfTokenController {
    @GetMapping
    public CsrfToken csrfToken(HttpServletRequest request) {
        // 세션 강제 생성 (여기가 핵심)
        request.getSession(true);

        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }
}
