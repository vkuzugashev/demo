package com.example.demo;
/**
 * Класс запроса
 */
public class UnBookingRequest {
    
    private String source;
    private Long carId;
    private String note;
    
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
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    
    @Override
    public String toString(){
        return String.format("source : %s, cardId : %d, note : %s", source, carId, note);
    }
}
