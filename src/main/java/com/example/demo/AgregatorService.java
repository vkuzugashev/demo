package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Сервис агрегации
 * author Кузугашев В. 2021
 */
@Service
public class AgregatorService {
    
   
    @Autowired
    private IConnectorService[] connectors;

    /**
     * Получить список свободных машин 
     * @param addr
     * @return
     * @throws Exception
     */
    public List<Car> FindCarByAddr(String addr) throws Exception {
        
        List<CompletableFuture<List<Car>>> _connectors = new ArrayList<CompletableFuture<List<Car>>>();

        for (IConnectorService item : connectors) {
            _connectors.add(item.FindCarByAddr(addr));
        }
        
        CompletableFuture.allOf(_connectors.toArray(new CompletableFuture[0])).join();
        
        List<Car> allCars = new ArrayList<Car>();

        for (CompletableFuture<List<Car>> item : _connectors){
            allCars.addAll(item.get());
        }
        
        return allCars;
    }
    
    /**
     * Забронировать машину
     * @param source
     * @param carId
     * @param phone
     * @param addr
     * @return
     * @throws Exception
     */
    public Boolean Booking(String source, long carId, String phone, String addr) throws Exception { 
        
        CompletableFuture<Boolean> result=null;

        for(IConnectorService item : connectors){
            if(source.equals(item.getSource())){
                result = item.Booking(carId, phone, addr);
                CompletableFuture.allOf(result).join();
                return result.get();
            }
        }
        
        //Нет коннектора в списке сгенерим ошибку
        throw new Exception("Нет источника!");
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

        for(IConnectorService item : connectors){
            if(source.equals(item.getSource())){
                result = item.UnBooking(carId, note);
                CompletableFuture.allOf(result).join();
                return result.get();
            }
        }
        
        //Нет коннектора в списке сгенерим ошибку
        throw new Exception("Нет источника!");

    }

}
