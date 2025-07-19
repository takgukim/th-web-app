package com.developerleetaehee.th_web_app.controller.api;

import com.developerleetaehee.th_web_app.config.NaverMapProperties;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/map")
@Tag(name = "지도 연동 API", description = "지도 연동 시 필요한 위도,경고, 키 조회를 위한 API")
public class MapApiController {

    private final NaverMapProperties naverMapProperties;

    @GetMapping("/shop_location")
    @Operation(summary = "홈페이지 > 찾아오는 길 API 정보", description = "지도 고유키, 위도, 경도 정보 반환한다.")
    public Map<String, Object> findCompanyLocation() {
        // Map 은 순서 보장안함
        // LinkedHashMap 은 순서 보장함
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("lat", naverMapProperties.getCompanyLat());
        map.put("lng", naverMapProperties.getCompanyLng());

        return map;
    }
}
