package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ResultResponse implements IResultResponse {
    
    private Boolean result = true;
    private Object data;
    
    @Override
    public Boolean getResult() {
        return result;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResultResponse(Object data) {
        this.data = data;
    }

}

