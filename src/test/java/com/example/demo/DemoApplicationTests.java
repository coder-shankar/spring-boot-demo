package com.example.demo;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


class DemoApplicationTests {

    @Test
    void contextLoads() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("prajwol12345");
        System.out.println("encode = " + encode);
    }


    @Test
    void contextLoads1() {
        List<LineItem> lineItems = Arrays.asList(new LineItem());


        List<LineItem> lineItemList = lineItems.stream()
                                               .filter(lineItem -> lineItem.getLineItemId() == "filter data")
                                               .collect(Collectors.toList());
        Integer sum = lineItemList.stream().map(LineItem::getQuantity).reduce(0, Integer::sum);


    }

    @Data
    public static class LineItem {
        private Object item;
        private int quantity;
        private String lineItemId;
    }
}
