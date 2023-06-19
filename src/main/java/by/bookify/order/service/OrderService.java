package by.bookify.order.service;

import by.bookify.order.model.Order;
import by.bookify.order.model.OrderCreateDto;
import by.bookify.order.model.OrderStatus;

import java.util.List;

public interface OrderService {
    List<Order> findAll();

    List<Order> findByUserId(Long userId);

    List<Order> findByStatus(String status);

    void create(OrderCreateDto dto, String userEmail);

    void updateStatus(Long orderId, OrderStatus orderStatus);
}
