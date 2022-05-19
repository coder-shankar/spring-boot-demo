package com.example.demo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "spring")
public class CustomProperties {

    private Map<String, String> datasource = new HashMap<>();

    public Map<String, String> getDatasource() {
        return datasource;
    }
}
