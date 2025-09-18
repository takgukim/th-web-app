package com.developerleetaehee.th_web_app.di;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyDiController {

    private final MyWorker myWorker;

    @GetMapping("/di-sample")
    public String diSample() {
        System.out.println("의존성주입 DI 예제");
        return myWorker.doWork();
    }
}
