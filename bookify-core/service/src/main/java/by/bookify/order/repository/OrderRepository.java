package by.bookify.order.repository;

import by.bookify.domain.order.model.Order;
import by.bookify.domain.order.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();

    List<Order> findByUserId(Long userId);

    List<Order> findByStatus(OrderStatus status);
}
