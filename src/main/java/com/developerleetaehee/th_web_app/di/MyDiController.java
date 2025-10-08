package com.developerleetaehee.th_web_app.di;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "의존성 주입 활용", description = "의존성 주입을 활용하기 위한 스터디 자료")
public class MyDiController {

    private final MyWorker myWorker;

    @GetMapping("/di-sample")
    public String diSample() {
        System.out.println("의존성주입 DI 예제");
        return myWorker.doWork();
    }
}
