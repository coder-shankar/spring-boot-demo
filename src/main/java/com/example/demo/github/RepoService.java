package com.example.demo.github;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepoService {

    @Autowired
    private RepoDao dao;

    @Autowired
    private ObjectMapper mapper;

    public List<Repo> get() throws Exception{
        RepoDto abc = mapper.readValue("{\"name\":\"John\"}", RepoDto.class);
        System.out.println("abc = " + abc);
        return dao.findAll();
    }

    public Repo save (Repo repo) {
        return dao.save(repo);
    }

    public List<Repo> saveList(List<Repo> repos) {
    return dao.saveAll(repos);
    }


}
