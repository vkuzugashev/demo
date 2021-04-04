package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Сервис агрегации
 * author Кузугашев В. 2021
 */
@Service
public class AgregatorService {
    
    private final IConnectorService customConnector;
    private final IConnectorService customConnector2;

    public AgregatorService(CustomConnectorService customConnector, Custom2ConnectorService customConnector2){
        this.customConnector = customConnector;
        this.customConnector2= customConnector2;
    }

    /**
     * Получить список свободных машин 
     * @param addr
     * @return
     * @throws Exception
     */
    public Collection<Car> FindCarByAddr(String addr) throws Exception {
        
        CompletableFuture<Collection<Car>> _cars = this.customConnector.FindCarByAddr(addr);
        CompletableFuture<Collection<Car>> _cars2 = this.customConnector2.FindCarByAddr(addr);
        
        CompletableFuture.allOf(_cars, _cars2).join();
        
        Collection<Car> cars = _cars.get();
        Collection<Car> cars2 = _cars2.get();
        
        Collection<Car> allCars = new ArrayList<Car>();
        allCars.addAll(cars);        
        allCars.addAll(cars2);        
        
        return allCars;
    }
    
    /**
     * Заброниловать машину
     * @param source
     * @param carId
     * @param phone
     * @param addr
     * @return
     * @throws Exception
     */
    public Boolean Booking(String source, long carId, String phone, String addr) throws Exception { 
        
        CompletableFuture<Boolean> result=null;
        if(source.equals(this.customConnector.getSource()))
            result = this.customConnector.Booking(carId, phone, addr);
        else if(source.equals(this.customConnector2.getSource()))
            result = this.customConnector2.Booking(carId, phone, addr);
        else
            throw new Exception("Нет источника!");

        CompletableFuture.allOf(result).join();
        
        return result.get();

    }

    /**
     * Снять бронь с машины
     * @param source
     * @param carId
     * @param note
     * @return
     * @throws Exception
     */
    public Boolean UnBooking(String source, long carId, String note) throws Exception {
        
        CompletableFuture<Boolean> result=null;

        if(source.equals(this.customConnector.getSource()))
            result = this.customConnector.UnBooking(carId, note);
        else if(source.equals(this.customConnector2.getSource()))
            result = this.customConnector2.UnBooking(carId, note);
        else
            throw new Exception("Нет источника!");

        CompletableFuture.allOf(result).join();

        return result.get();
    }

}
