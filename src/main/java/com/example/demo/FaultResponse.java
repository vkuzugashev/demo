package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Класс возврата ошибки
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class FaultResponse implements IResultResponse {

    private Boolean result = false;
    private String message;

    @Override
    public Boolean getResult() {
        return result;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
    
    @Override
    public Object getData()
    {
        return null;
    }

    @Override
    public void setData(Object data) { }

    public void setMessage(String message) {
        this.message = message;
    }

    public FaultResponse(String message) {
        this.message = message;
    }

}
