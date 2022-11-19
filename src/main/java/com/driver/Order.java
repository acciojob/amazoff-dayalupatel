package com.driver;

import com.driver.Helper.TimeConverter;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, int deliveryTime) {
        this.id = id;
        this.deliveryTime = deliveryTime;
    }

    public Order(String id, String deliveryTime) {
        this.id = id;
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        int intDeliveryTime = TimeConverter.convertTimeStringToInt(deliveryTime);        
        this.deliveryTime = intDeliveryTime;

    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }
}
