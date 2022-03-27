package com.example.demo.data;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "first", column = @Column(name = "first_name")),
            @AttributeOverride(name = "last", column = @Column(name = "name_name"))
    })
    private Name name;

}



