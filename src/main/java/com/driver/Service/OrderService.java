package com.driver.Service;

import java.util.List;

import com.driver.DeliveryPartner;
import com.driver.Order;

public interface OrderService {
    // 1. Add an Order:
    void addOrder(Order order);

    // 2. Add a Delivery Partner:
    void addPartner(String  partnerId);

    // 3. Assign an order to a partner: 
    void addOrderPartnerPair(String orderId, String partnerId);

    // 4. Get Order by orderId:
    Order getOrderById(String orderId);

    // 5. Get Partner by partnerId:
    DeliveryPartner  getPartnerById(String partnerId);

    // 6. Get number of orders assigned to given partnerId:
    int getOrderCountByPartnerId(String partnerId);

    // 7. Get List of all orders assigned to given partnerId:
    List<String> getOrdersByPartnerId(String partnerId);

    // 8. Get List of all orders in the system:
    List<String> getAllOrders();

    // 9. Get count of orders which are not assigned to any partner:
    int getCountOfUnassignedOrders();

    // 10. Get count of orders which are left undelivered by partnerId after given time:
    int getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId);
    
    // 11. Get the time at which the last delivery is made by given partner:
    String getLastDeliveryTimeByPartnerId(String partnerId);

    // 12. Delete a partner and the corresponding orders should be unassigned: 
    void deletePartnerById(String partnerId);

    // 13. Delete an order and the corresponding partner should be unassigned:
    void deleteOrderById(String orderId);
}
