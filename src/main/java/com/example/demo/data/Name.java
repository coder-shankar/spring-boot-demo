package com.example.demo.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Name {
    private String first;
    private String middle;
    private String last;
}
