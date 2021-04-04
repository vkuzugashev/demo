package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Класс возврата ошибки
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class FaultResponse {

    private String message;

    public String getResult() {
        return "ERROR";
    }
    
    public String getMessage() {
        return message;
    }
    
    public FaultResponse(String message){
        this.message = message;
    }
}
