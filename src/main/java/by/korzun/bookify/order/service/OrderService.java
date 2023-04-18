package by.korzun.bookify.order.service;

import by.korzun.bookify.order.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    List<Order> findByUserId(Long userId);
    List<Order> findByStatus(String status);
    void createOrder(List<Long> bookIds, String userEmail);
    void deliverOrderByOrderId(Long orderId);
}
