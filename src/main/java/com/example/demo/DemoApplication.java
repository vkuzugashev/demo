package com.example.demo;

import java.util.Collection;
import java.util.concurrent.Executor;

import org.apache.catalina.connector.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	/**
	 * Главный обработчик ошибок
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
    public FaultResponse handleException(Exception e) {
        return new FaultResponse(e.getMessage());
    }

	@GetMapping("/FindByAddr")
	public SuccessResponse FindByAddr(@RequestParam(name = "addr", required = true) String addr) throws Exception {
			
		try{
			Collection<Car> cars;		
			long start = System.currentTimeMillis();
			System.out.printf("Start DemoApplication.FindCarByAddr %s\r\n", addr);	
			cars = this.agregatorService.FindCarByAddr(addr);
			System.out.printf("Stop DemoApplication.FindCarByAddr %s, time %d ms\r\n", addr, (System.currentTimeMillis() - start));	
			return new SuccessResponse(cars);
		}
		catch(Exception e){
			throw new Exception(e.getMessage());
		}
		
	}

	@GetMapping("/Booking")
	public SuccessResponse Booking(
		@RequestParam(name = "source", required = true) String source,
		@RequestParam(name = "carId", required = true) Long carId,
		@RequestParam(name = "phone", required = true) String phone,
		@RequestParam(name = "addr", required = true) String addr
	) throws Exception {

		try{
			Boolean result = this.agregatorService.Booking(source, carId, phone, addr);
			return new SuccessResponse(result);
		}
		catch(Exception e){
			throw new Exception(e.getMessage());
		}

	}

	@GetMapping("/UnBooking")
	public SuccessResponse UnBooking(
		@RequestParam(name = "source", required = true) String source,
		@RequestParam(name = "carId", required = true) Long carId,
		@RequestParam(name = "note", required = true) String note		
	) throws Exception {
		
		try{
			Boolean result = this.agregatorService.UnBooking(source, carId, note);
			return new SuccessResponse(result);
		}
		catch(Exception e){
			throw new Exception(e.getMessage());
		}	

	}
}
