package com.developerleetaehee.th_web_app.di;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyWorker {

    private final MyReader myReader;

    public String doWork() {
        return "Worker Dependency Injection msg :  " + myReader.read();
    }
}
