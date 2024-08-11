package com.example.GitHubApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableAsync
public class GitHubConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder().defaultHeader(HttpHeaders.AUTHORIZATION, "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").baseUrl("https://api.github.com").build();
    }
}
