package com.example.demo;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Демо приложение с демонстрацией асинхронных вызовов внешних коннекторов
 * author Кузугашев В. 2021
 */
@SpringBootApplication
@RestController
@EnableAsync(proxyTargetClass=true)
@RequestMapping("api")
public class DemoApplication {

	private AgregatorService agregatorService;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/**
	 * Задаём число потоков в пуле для асинхронных вызовов
	 * @return
	 */
	@Bean
	public Executor taskExecutor() {
	  ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	  executor.setCorePoolSize(4);
	  executor.setMaxPoolSize(4);
	  executor.setQueueCapacity(500);
	  executor.setThreadNamePrefix("AgregatorService-");
	  executor.initialize();
	  return executor;
	}

	public DemoApplication(AgregatorService agregatorService){
		this.agregatorService = agregatorService;
	}
	
	@GetMapping("/FindByAddr")
	public IResultResponse FindByAddr(@RequestParam(value = "addr", defaultValue = "") String addr) {
		
		IResultResponse result;
		Collection<Car> cars;		
			
		try{
			long start = System.currentTimeMillis();
			System.out.printf("Start DemoApplication.FindCarByAddr %s\r\n", addr);	
			cars = this.agregatorService.FindCarByAddr(addr);
			System.out.printf("Stop DemoApplication.FindCarByAddr %s, time %d ms\r\n", addr, (System.currentTimeMillis() - start));	
			result = new ResultResponse(cars);
		}
		catch(Exception e){
			result = new FaultResponse(e.getMessage());
		}
	
		return result;
	}

	@GetMapping("/Booking")
	public IResultResponse Booking(
		@RequestParam(value = "source", defaultValue = "") String source,
		@RequestParam(value = "carId", defaultValue = "") Long carId,
		@RequestParam(value = "phone", defaultValue = "") String phone,
		@RequestParam(value = "addr", defaultValue = "") String addr
	) {
		Boolean result;
		try{
			result = this.agregatorService.Booking(source, carId, phone, addr);
			return new ResultResponse(result);
		}
		catch(Exception e){
			return new FaultResponse(e.getMessage());
		}
	}

	@GetMapping("/UnBooking")
	public String UnBooking(
		@RequestParam(value = "source", defaultValue = "") String source,
		@RequestParam(value = "carId", defaultValue = "") Long carId,
		@RequestParam(value = "note", defaultValue = "") String note		
	) {
		return String.format("UnBooking %s, %d, %s!", source, carId, note);		
	}
}
