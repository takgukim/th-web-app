package com.developerleetaehee.th_web_app.di;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyReader {
    public String read() {
        return "Hello from MyReader Dependency Injection!!!!!";
    }
}
