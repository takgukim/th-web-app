package com.developerleetaehee.th_web_app.batch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class HelloWorldScheduler {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Value("${LOGGING_FOLDER:${user.home}/logs/batch}") // 기본값 logs
    private String logFolder;

    @Scheduled(cron = "${scheduler.hello-world-cron}")
    public void writeHelloWorld() {

        System.out.println("System.out.println()도 도커, 서버에서 잘 작동해요. 100");

        String date = LocalDate.now().format(DATE_FORMATTER);
        String fileName = this.logFolder + "/hello_world_" + date + ".log";

        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write("Hello_world_" + date + System.lineSeparator());
            System.out.println("로그 작성 완료 : " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
