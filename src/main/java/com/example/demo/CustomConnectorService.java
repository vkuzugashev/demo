package com.example.demo;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Сервис коннектора на базе абстрактого класса
 * author Кузугашев В. 2021
 */
@Service
public class CustomConnectorService extends AbstractConnectorService {
	
	// Для взаимедействия с внешними сервисами, тут не используется
	private final RestTemplate restTemplate;

	public CustomConnectorService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
		this.setSource("Taxi_1");
	}


	@Override
	@Async
	public CompletableFuture<List<Car>> FindCarByAddr(String addr) throws InterruptedException {
		// для примера вызовем родительский метод	
		System.out.printf("%s\r\n", CustomConnectorService.class);	
		return super.FindCarByAddr(addr);
	}
	
	@Override
	@Async
	public CompletableFuture<Boolean> Booking(long carId, String phone, String addr) throws InterruptedException {
		//Для примера вызовем родительски метод как заглужку
		return super.Booking(carId, phone, addr);
	}
	
	@Override
	@Async
	public CompletableFuture<Boolean> UnBooking(long carId, String note) throws InterruptedException {
		//Для примера вызовем родительский метод как заглужку
		return super.UnBooking(carId, note);
	}
}