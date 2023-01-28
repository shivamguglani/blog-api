package com.backend.spring.boot.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class ApiResponse {
    private String message;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;

    }

    private boolean success;

    private Map<String,String> warnings;


}
