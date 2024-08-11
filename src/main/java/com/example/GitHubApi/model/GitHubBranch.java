package com.example.GitHubApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.LinkedHashMap;

@Data
public class GitHubBranch {
    private String name;
    private LinkedHashMap<String,Object> commit;
    private String lastCommitSha;

    public void fillLastCommitSha() {
        lastCommitSha = commit.get("sha").toString();
    }

    private LinkedHashMap<String, Object> getCommit() {
        return commit;
    }
}
