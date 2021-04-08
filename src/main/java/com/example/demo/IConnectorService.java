package com.example.demo;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Интерфейс коннектора
 * author Кузугашев В. 2021
 */
public interface IConnectorService {   
    String getSource();
    CompletableFuture<List<Car>> FindCarByAddr(String addr) throws InterruptedException;
    CompletableFuture<Boolean> Booking(long carId, String phone, String addr) throws InterruptedException;
    CompletableFuture<Boolean> UnBooking(long carId, String note) throws InterruptedException;
}
