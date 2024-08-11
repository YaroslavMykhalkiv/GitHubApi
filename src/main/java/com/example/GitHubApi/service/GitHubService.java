package com.example.GitHubApi.service;

import com.example.GitHubApi.model.GitHubBranch;
import com.example.GitHubApi.model.GitHubRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class GitHubService {


    private WebClient webClient;

    public GitHubService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<GitHubRepository> getReposByUsername(String username) {
        List<GitHubRepository> reposList = getAllRepoNamesAndOwner(username);
        reposList.forEach(repo->{
            System.out.println("sent request");
            repo.setBranches(getAllBranchesAndLastCommitSha(username, repo.getName()));
            System.out.println("get responce");

        });
        return reposList;
    }

    private List<GitHubRepository> getAllRepoNamesAndOwner(String username) {
        List<GitHubRepository> response = webClient.get()
                .uri("users/" + username + "/repos")
                .retrieve()
                .toEntityList(GitHubRepository.class)
                .block()
                .getBody();
        response.forEach(GitHubRepository::fillOwnerLogin);
        return response;
    }

    @Async
    protected List<GitHubBranch> getAllBranchesAndLastCommitSha(String username, String repoName) {
        List<GitHubBranch> response = webClient.get()
                .uri("repos/" + username + "/" + repoName + "/branches")
                .retrieve()
                .toEntityList(GitHubBranch.class)
                .block()
                .getBody();
        response.forEach(GitHubBranch::fillLastCommitSha);
        return response;
    }
}
