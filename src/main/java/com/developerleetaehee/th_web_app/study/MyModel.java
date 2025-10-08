package com.developerleetaehee.th_web_app.study;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Object Mapper 활용하기 위해 만든 클래스")
public class MyModel {
    private String name;
    private int age;
}
