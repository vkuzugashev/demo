package com.example.demo;

import java.util.List;
import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	/**
	 * Сервис агрегации
	 */
	@Autowired
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


	/**
	 * Главный обработчик ошибок
	 * @param e
	 * @return
	 */
	@ExceptionHandler
    public FaultResponse handleException(Exception e) {
		System.out.printf("Exception -> %s\r\n", e.getMessage());
        return new FaultResponse(e.getMessage());
    }

	@GetMapping("/FindByAddr")
	public SuccessResponse FindByAddr(@RequestParam(name = "addr", required = true) String addr) throws Exception {
		
		List<Car> cars;		
		long start = System.currentTimeMillis();
		System.out.printf("Start DemoApplication.FindCarByAddr %s\r\n", addr);	
		cars = this.agregatorService.FindCarByAddr(addr);
		System.out.printf("Stop DemoApplication.FindCarByAddr %s, time %d ms\r\n", addr, (System.currentTimeMillis() - start));	
		return new SuccessResponse(cars);

	}

	@PostMapping("/Booking")
	public SuccessResponse Booking(@RequestBody(required = true) BookingRequest req) throws Exception {
		
		Boolean result = this.agregatorService.Booking(req.getSource(), req.getCarId(), req.getPhone(), req.getAddr());
		return new SuccessResponse(result);

	}

	@PostMapping("/UnBooking")
	public SuccessResponse UnBooking(@RequestBody(required = true) UnBookingRequest req) throws Exception {

		Boolean result = this.agregatorService.UnBooking(req.getSource(), req.getCarId(), req.getNote());
		return new SuccessResponse(result);

	}
}
