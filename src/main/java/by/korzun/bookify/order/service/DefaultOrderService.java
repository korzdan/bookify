package by.korzun.bookify.order.service;

import by.korzun.bookify.book.service.BookService;
import by.korzun.bookify.order.exception.OrderNotFound;
import by.korzun.bookify.order.model.CreateOrderDto;
import by.korzun.bookify.order.model.Order;
import by.korzun.bookify.order.model.OrderStatus;
import by.korzun.bookify.order.repository.OrderRepository;
import by.korzun.bookify.statistics.service.StatisticsService;
import by.korzun.bookify.user.model.User;
import by.korzun.bookify.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final BookService bookService;
    private final StatisticsService statisticsService;

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
    public void createOrder(CreateOrderDto dto, String userEmail) {
        User user = userService.loadUserByUsername(userEmail);
        Order order = new Order()
                .setStatus(OrderStatus.PENDING)
                .setUser(user)
                .setBooks(
                        dto.getBookIds().stream()
                                .map(bookService::findById)
                                .collect(Collectors.toList())
                )
                .setAddress(dto.getAddress())
                .setComment(dto.getComment())
                .setTotalPrice(dto.getTotalPrice());
        updateStorageNumberAndOrderNum(dto);
        statisticsService.incOrders();
        userService.updateOrderNum(user);
        orderRepository.save(order);
    }

    private void updateStorageNumberAndOrderNum(CreateOrderDto dto) {
        dto.getBookIds().stream()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                .forEach((key, value) ->
                        bookService.decrementStorageNumAndIncOrderNum(key, value.intValue()));
    }

    @Override
    public void deliverOrderByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFound("Такого заказа не существует."));
        order.setStatus(OrderStatus.DELIVERED);
        orderRepository.save(order);
    }

    @Override
    public void deliveringOrderByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFound("Такого заказа не существует."));
        order.setStatus(OrderStatus.DELIVERING);
        orderRepository.save(order);
    }
}
