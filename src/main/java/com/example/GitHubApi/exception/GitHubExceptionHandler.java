package com.example.GitHubApi.exception;

import com.example.GitHubApi.model.MyErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@ControllerAdvice
public class GitHubExceptionHandler {

        @ExceptionHandler(WebClientResponseException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        @ResponseBody
        public MyErrorResponse handleResourceNotFoundException(Exception ex) {
            return new MyErrorResponse("Probably not existing username - " + ex.getMessage());
        }
}
