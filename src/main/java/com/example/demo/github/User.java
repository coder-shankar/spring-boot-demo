package com.example.demo.github;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User {

    @Id
    private int id;

    @ManyToOne
    private User parent;

    @OneToMany(mappedBy = "parent")
    private List<User> following;

    @OneToMany(mappedBy = "parent")
    private List<User> followers;
}
