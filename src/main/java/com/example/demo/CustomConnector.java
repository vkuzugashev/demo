package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * Коннектор
 * author Кузугашев В. 2021
 */
@Repository
public class CustomConnector implements IConnector {

	private final String source = "Taxi_1";
	private final RestTemplate restTemplate;
	
	public CustomConnector(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public String getSource() {
		return this.source;
	}

	@Async
	public CompletableFuture<Collection<Car>> FindCarByAddr(String addr) throws InterruptedException {
		
		// Должно быть что-то подобное обращение к методу REST источника
		// logger.info("Looking up " + user);
    	// String url = String.format("https://api.github.com/users/%s", user);
    	// User results = restTemplate.getForObject(url, User.class);
    	//return CompletableFuture.completedFuture(results);
		
		long start = System.currentTimeMillis();
		System.out.printf("Start FindCarByAddr %s\r\n", addr);			

		// Имитция загрузки из источника
		Thread.sleep(3000);
		System.out.printf("Stop FindCarByAddr %s, time %d ms\r\n", addr, (System.currentTimeMillis() - start));			
		
		Collection<Car> cars = new ArrayList<Car>();
		cars.add(new Car(this.source, 1L, "xxXX1xx42", "Лада2103", "8903XXXXXXXX"));
		cars.add(new Car(this.source, 2L, "xxXX2xx42", "Лада2106", "8903XXXXXXXX"));
		cars.add(new Car(this.source, 3L, "xxXX3xx42", "Лада2121", "8903XXXXXXXX"));

		return CompletableFuture.completedFuture(cars);
	}
	
	@Async
	public CompletableFuture<Boolean> Booking(long carId, String phone, String addr) throws InterruptedException {
		System.out.printf("Start Booking %d, %s, %s\r\n", carId, phone, addr);	
		// Имитация задержки сохранения
		Thread.sleep(3000);
		System.out.printf("Stop Booking %d, %s, %s\r\n", carId, phone, addr);	
		return CompletableFuture.completedFuture(true);
	}

	@Async
	public CompletableFuture<Boolean> UnBooking(long carId, String note) throws InterruptedException {
		System.out.printf("Start UnBooking %d, %s\r\n", carId, note);			
		// Имитация задержки сохранения
		Thread.sleep(3000);
		System.out.printf("Stop UnBooking %d, %s\r\n", carId, note);	
		return CompletableFuture.completedFuture(true);		
	}
}