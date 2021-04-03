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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    @Override
    public String toString(){
        return String.format("Car {%s, %d, %s, %s, %s}", source, carId, carNumber, carModel, driverPhone );
    }
}
