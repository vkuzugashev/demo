package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SuccessResponse {
    
    private Object data;
    
    public String getResult() {
        return "OK";
    }

    public Object getData() {
        return data;
    }

    public SuccessResponse(Object data){
        this.data = data;
    }
}

