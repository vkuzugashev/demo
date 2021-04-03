package com.example.demo;

/**
 * Интерфейс результата
 */
public interface IResultResponse {
    Boolean getResult();
    String getMessage() ;
    Object getData();
    void setData(Object data);
}
