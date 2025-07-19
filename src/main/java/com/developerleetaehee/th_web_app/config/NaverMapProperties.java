package com.developerleetaehee.th_web_app.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "naver.map")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NaverMapProperties {
    private String clientId;
    private double companyLat;
    private double companyLng;
}
