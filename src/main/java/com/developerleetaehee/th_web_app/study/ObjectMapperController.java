package com.developerleetaehee.th_web_app.study;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/study")
@Tag(name = "Object Mapper 활용", description = "Object Mapper를 활용하기 위해서 학습코드")
public class ObjectMapperController {

    @GetMapping("/object_mapping_test")
    public String objectMappingTest() {

        MyModel myModel = new MyModel("이태희", 26);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> params = objectMapper.convertValue(
                myModel,
                new TypeReference<Map<String, Object>>() {}
        );

        params.put("register_date", "2025-09-18");
        params.put("user_id", "abcdefghi1234");

        params.forEach((k, v) -> System.out.println(k + " : " + v));

        return "콘솔에 params 내용이 출력 되었습니다.";
    }
}
