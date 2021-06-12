package com.example.demo.github;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoDao  extends JpaRepository<Repo, Long> {
}
