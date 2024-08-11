package com.example.GitHubApi.controller;

import com.example.GitHubApi.model.GitHubRepository;
import com.example.GitHubApi.service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class GitHubRepoController {

    @Autowired
    GitHubService gitHubService;


    @GetMapping("/api/github/{username}")
    public List<GitHubRepository> getAllReposByUsername(@PathVariable String username) {
        return gitHubService.getReposByUsername(username);
    }


}
