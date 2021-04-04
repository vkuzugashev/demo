package com.example.demo;
/**
 * Класс запроса
 */
public class BookingRequest {
    
    private String source;
    private Long carId;
    private String phone;
    private String addr;

    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public Long getCarId() {
        return carId;
    }
    public void setCarId(Long carId) {
        this.carId = carId;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddr() {
        return addr;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }
    
    @Override
    public String toString(){
        return String.format("source : %s, cardId : %d, phone : %s, addr : %s", source, carId, phone, addr);
    }
}
