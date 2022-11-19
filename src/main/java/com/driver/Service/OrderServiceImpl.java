package com.driver.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.DeliveryPartner;
import com.driver.Order;
import com.driver.Repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public void addOrder(Order order) {
        orderRepository.addOrder(order);
    }

    @Override
    public void addPartner(String partnerId) {
        orderRepository.addPartner(partnerId);
    }

    @Override
    public void addOrderPartnerPair(String orderId, String partnerId) {
        orderRepository.addOrderPartnerPair(orderId, partnerId);
    }

    @Override
    public Order getOrderById(String orderId) {
        return orderRepository.getOrderById(orderId);
    }

    @Override
    public DeliveryPartner getPartnerById(String partnerId) {
        return orderRepository.getPartnerById(partnerId);
    }

    @Override
    public int getOrderCountByPartnerId(String partnerId) {
        return orderRepository.getOrderCountByPartnerId(partnerId);
    }

    @Override
    public List<String> getOrdersByPartnerId(String partnerId) {
        return orderRepository.getOrdersByPartnerId(partnerId);
    }

    @Override
    public List<String> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    @Override
    public int getCountOfUnassignedOrders() {
        return orderRepository.getCountOfUnassignedOrders();
    }

    @Override
    public int getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time, partnerId);
    }

    @Override
    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        return orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
    }

    @Override
    public void deletePartnerById(String partnerId) {
        orderRepository.deletePartnerById(partnerId);
        
    }

    @Override
    public void deleteOrderById(String orderId) {
        orderRepository.deleteOrderById(orderId);        
    }
    
}
