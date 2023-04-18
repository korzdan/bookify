package by.korzun.bookify.order.service;

import by.korzun.bookify.book.service.BookService;
import by.korzun.bookify.order.exception.OrderNotFound;
import by.korzun.bookify.order.model.Order;
import by.korzun.bookify.order.model.OrderStatus;
import by.korzun.bookify.order.repository.OrderRepository;
import by.korzun.bookify.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultOrderRepository implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final BookService bookService;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> findByStatus(String status) {
        try {
            OrderStatus orderStatus = OrderStatus.valueOf(status);
            return orderRepository.findByStatus(orderStatus);
        } catch (IllegalArgumentException e) {
            throw new OrderNotFound("Такого статуса не существует.");
        }
    }

    @Override
    public void createOrder(List<Long> bookIds, String userEmail) {
        Order order = new Order()
                .setStatus(OrderStatus.PENDING)
                .setUser(userService.loadUserByUsername(userEmail))
                .setBooks(
                        bookIds.stream()
                                .map(bookService::findById)
                                .collect(Collectors.toList())
                );
        orderRepository.save(order);
    }

    @Override
    public void deliverOrderByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFound("Такого заказа не существует."));
        order.setStatus(OrderStatus.DELIVERED);
        orderRepository.save(order);
    }
}
