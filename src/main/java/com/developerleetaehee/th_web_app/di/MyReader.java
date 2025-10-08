package com.developerleetaehee.th_web_app.di;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Schema(description = "의존성 주입 클래스 테스트 클래스")
public class MyReader {
    public String read() {
        return "Hello from MyReader Dependency Injection!!!!!";
    }
}
