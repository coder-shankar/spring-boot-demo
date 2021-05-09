package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepoService {

    @Autowired
    private RepoDao dao;


    public List<Repo> get() {
        return dao.findAll();
    }

    public Repo save (Repo repo) {
        return dao.save(repo);
    }

    public List<Repo> save(List<Repo> repos) {
    return dao.saveAll(repos);
    }


}
