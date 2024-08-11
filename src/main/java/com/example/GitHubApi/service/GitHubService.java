package com.example.GitHubApi.service;

import com.example.GitHubApi.model.GitHubBranch;
import com.example.GitHubApi.model.GitHubRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class GitHubService {


    private WebClient webClient;

    public GitHubService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<GitHubRepository> getReposByUsername(String username) {
        List<GitHubRepository> reposList = getAllRepoNamesAndOwner(username);
        List<CompletableFuture<Void>> futures = reposList.stream()
                .map(repo -> CompletableFuture.runAsync(() -> {
                    try {
                        List<GitHubBranch> branches = getAllBranchesAndLastCommitSha(username, repo.getName()).get();
                        repo.setBranches(branches);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }))
                .collect(Collectors.toList());
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        return reposList;
    }

    private List<GitHubRepository> getAllRepoNamesAndOwner(String username) {
        List<GitHubRepository> response = webClient.get()
                .uri("users/" + username + "/repos")
                .retrieve()
                .toEntityList(GitHubRepository.class)
                .block()
                .getBody();

        response = response.stream().filter(repo->{
            if(repo.isFork()) {
                return false;
            }
            repo.fillOwnerLogin();
            return true;
        }).collect(Collectors.toList());
        return response;
    }

    @Async
    protected CompletableFuture<List<GitHubBranch>> getAllBranchesAndLastCommitSha(String username, String repoName) throws ExecutionException, InterruptedException {
        CompletableFuture<List<GitHubBranch>> response = webClient.get()
                .uri("repos/" + username + "/" + repoName + "/branches")
                .retrieve()
                .bodyToFlux(GitHubBranch.class)
                .collectList()
                .toFuture();
        response.get().forEach(GitHubBranch::fillLastCommitSha);
        return response;
    }
}
