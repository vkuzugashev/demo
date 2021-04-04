package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Класс машины такси
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Car {
    private String source;
    private long carId;
    private String carNumber;
    private String carModel;
    private String driverPhone;
    
    public Car(String source, Long carId, String carNumber, String carModel, String driverPhone){
        this.source = source;
        this.carId = carId;
        this.carNumber = carNumber;
        this.carModel = carModel;
        this.driverPhone = driverPhone;
    }

    /**
     * Получить наименование источника
     * @return
     */
    public String getSource() {
        return source;
    }

    /**
     * Установить наименование истоника
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Получить ID машины
     */
    public long getCarId() {
        return carId;
    }

    /**
     * Установить ID машины
     * @param carId
     */
    public void setCarId(long carId) {
        this.carId = carId;
    }

    /**
     * Получить номер машины
     * @return
     */
    public String getCarNumber() {
        return carNumber;
    }

    /**
     * Установить номер машины
     * @param carNumber
     */
    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    /**
     * Получить модель машины
     */
    public String getCarModel() {
        return carModel;
    }

    /**
     * Установить модель машины
     */
    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    /**
     * Получить телефон водителя
     * @return
     */
    public String getDriverPhone() {
        return driverPhone;
    }

    /**
     * Установить телефон водителя
     * @param driverPhone
     */
    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    @Override
    public String toString(){
        return String.format("Car {%s, %d, %s, %s, %s}", source, carId, carNumber, carModel, driverPhone );
    }
}
