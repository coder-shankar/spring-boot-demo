package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Map;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    CustomProperties customProperties;

    @Override
    public void run(String... args) throws Exception {
        Map<String, String> maped = customProperties.getDatasource();

        System.out.println("maped = " + maped);
        BigDecimal bigDecimal = new BigDecimal(433232.2).setScale(1, RoundingMode.DOWN);
        System.out.println("bigDecimal = " + bigDecimal);
        System.out.println("Arrays.deepToString(args) = " + Arrays.deepToString(args));
    }
}
