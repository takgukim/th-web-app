package com.developerleetaehee.th_web_app.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "cors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CorsProperties {
    private List<String> allowedOrigins;
}
