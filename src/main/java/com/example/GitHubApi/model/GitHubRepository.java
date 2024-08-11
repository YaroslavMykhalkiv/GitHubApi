package com.example.GitHubApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;

@Data
public class GitHubRepository {
    private String name;
    private LinkedHashMap<String, Object> owner;
    private String ownerLogin;
    private List<GitHubBranch> branches;

    public void fillOwnerLogin() {
        ownerLogin = owner.get("login").toString();
    }

    private LinkedHashMap<String, Object> getOwner() {
        return owner;
    }
}
