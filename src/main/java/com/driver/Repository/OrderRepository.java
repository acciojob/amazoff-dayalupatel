package com.driver.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.driver.DeliveryPartner;
import com.driver.Order;
import com.driver.Helper.TimeConverter;
import com.driver.Service.OrderService;

@Repository
@Component
public class OrderRepository implements OrderService {
    HashMap<String, Order> orderDB;                       // storing orders
    HashMap<String, DeliveryPartner> partnerDB;           // storing partners
    HashMap<String, String> orderPairDB;                  // order -> partner;
    HashMap<String, List<String>> partnerPairDB;          // partner -> List<orderId> pair

    public OrderRepository() {
        orderDB = new HashMap<>();
        partnerDB = new HashMap<>();
        orderPairDB = new HashMap<>();
        partnerPairDB = new HashMap<>();
    }

    @Override
    public void addOrder(Order order) {
        orderDB.put(order.getId(), order);
    }

    @Override
    public void addPartner(String partnerId) {
        DeliveryPartner partner = new DeliveryPartner(partnerId);
        partnerDB.put(partnerId, partner);
    }

    @Override
    public void addOrderPartnerPair(String orderId, String partnerId) {
        // adding order -> partner (One To One)
        orderPairDB.put(orderId, partnerId);

        // adding partner-> List<Orders>  (One to Many)
        if(!partnerPairDB.containsKey(partnerId)) {
            partnerPairDB.put(partnerId, new ArrayList<>());
        }
        partnerPairDB.get(partnerId).add(orderId);
    }

    @Override
    public Order getOrderById(String orderId) {
        if(orderDB.containsKey(orderId)) {
            return orderDB.get(orderId);
        }
        return null;
    }

    @Override
    public DeliveryPartner getPartnerById(String partnerId) {
        if(partnerDB.containsKey(partnerId)) {
            DeliveryPartner partner = partnerDB.get(partnerId);
            if(partnerPairDB.containsKey(partnerId)) {
                partner.setNumberOfOrders(partnerPairDB.get(partnerId).size());
            }
            return partner;
        }
        return null;
    }

    @Override
    public int getOrderCountByPartnerId(String partnerId) {
        if(partnerPairDB.containsKey(partnerId)) {
            return partnerPairDB.get(partnerId).size();
        }
        return 0;
    }

    @Override
    public List<String> getOrdersByPartnerId(String partnerId) {
        if(partnerPairDB.containsKey(partnerId)) {
            return partnerPairDB.get(partnerId);
        }
        return null;
    }

    @Override
    public List<String> getAllOrders() {
        return new ArrayList<>(orderDB.keySet());
    }

    @Override
    public int getCountOfUnassignedOrders() {
        int count = 0;
        for(String orderId : orderDB.keySet()) {
            if(!orderPairDB.containsKey(orderId)) {
                count++;
            }
        }
        // return orderDB.size() - orderPairDB.size();  // this may be also correct
        return count;
    }

    @Override
    public int getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        if(!partnerPairDB.containsKey(partnerId)) {
            return 0;
        }

        int givenTime = TimeConverter.convertTimeStringToInt(time);
        
        List<String> orders = partnerPairDB.get(partnerId);
        int orderLeft = 0;
        for(String id : orders) {
            if(orderDB.get(id).getDeliveryTime()>givenTime) {
                orderLeft++;
            }
        }

        return orderLeft;
    }

    @Override
    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        //Return the time when that partnerId will deliver his last delivery order.
        if(!partnerPairDB.containsKey(partnerId)) {
            return null;
        }
        
        List<String> orders = partnerPairDB.get(partnerId);

        int time = 0;
        
        for(String id : orders) {
            int currTime = orderDB.get(id).getDeliveryTime();
            if(time < currTime ) {
                time = currTime;
            }
        }

        return TimeConverter.convertTimeIntToString(time);
        
    }

    @Override
    public void deletePartnerById(String partnerId) {
        //Deleting the partner with partnerId
        partnerDB.remove(partnerId);

        //Pushing all his assigned orders to unassigned orders.
        List<String> orders = partnerPairDB.get(partnerId); // assigned
        partnerPairDB.remove(partnerId);

        for(String id : orders) {
            orderPairDB.remove(id); // unassigned
        }
    }

    @Override
    public void deleteOrderById(String orderId) {
        //Deleting an order
        if(!orderDB.containsKey(orderId)) return;
        
        orderDB.remove(orderId);

        // remove it from the assigned order of that partnerId
        if(!orderPairDB.containsKey(orderId)) return;
        
        String partnerId = orderPairDB.get(orderId);
        
        List<String> orders = partnerPairDB.get(partnerId);
        for(int i=0;i<orders.size();i++) {
            if(orders.get(i).equals(orderId)) {
                orders.remove(i);  // order removed from list
                break;
            }
        }
        partnerPairDB.put(partnerId, orders);
    }

}
