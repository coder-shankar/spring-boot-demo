package com.example.demo.github;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.service.spi.InjectService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RepoServiceTest {

    @InjectMocks
    private  RepoService sut;

    @Mock
    private RepoDao dao;

    @Spy
    private ObjectMapper mapper; //real object

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void get() throws Exception {
        List<Repo> repos = sut.get();
        Assertions.assertEquals(0, repos.size());
    }

    @Test
    void save() {
    }

    @Test
    void saveList() {
    }
}
