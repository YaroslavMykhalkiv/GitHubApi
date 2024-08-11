package com.example.GitHubApi.model;

import lombok.Data;

@Data
public class MyErrorResponse {
    String status;
    String message;
    public MyErrorResponse(String message) {
        this.message = message;
        this.status = "404";
    }


}
