package by.korzun.bookify.order.repository;

import by.korzun.bookify.order.model.Order;
import by.korzun.bookify.order.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();
    List<Order> findByUserId(Long id);
    List<Order> findByStatus(OrderStatus status);
}
