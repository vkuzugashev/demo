package com.example.demo;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

/**
 * Интерфейс коннектора
 * author Кузугашев В. 2021
 */
public interface IConnector {   
    String getSource();
    CompletableFuture<Collection<Car>> FindCarByAddr(String addr) throws InterruptedException;
    CompletableFuture<Boolean> Booking(long carId, String phone, String addr) throws InterruptedException;
    CompletableFuture<Boolean> UnBooking(long carId, String note) throws InterruptedException;
}
