package by.bookify.order.service;

import by.bookify.domain.order.dto.OrderCreateDto;
import by.bookify.domain.order.model.Order;
import by.bookify.domain.order.model.OrderStatus;

import java.util.List;

public interface OrderService {
    List<Order> findAll();

    List<Order> findByUserId(Long userId);

    List<Order> findByStatus(String status);

    Order create(OrderCreateDto dto, String userEmail);

    Order updateStatus(Long orderId, OrderStatus orderStatus);
}
