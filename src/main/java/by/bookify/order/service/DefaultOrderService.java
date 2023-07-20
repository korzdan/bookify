package by.bookify.order.service;

import by.bookify.book.service.BookService;
import by.bookify.order.exception.OrderNotFoundException;
import by.bookify.order.exception.StatusNotFoundException;
import by.bookify.order.model.Order;
import by.bookify.order.model.OrderCreateDto;
import by.bookify.order.model.OrderStatus;
import by.bookify.order.repository.OrderRepository;
import by.bookify.statistics.service.StatisticsService;
import by.bookify.user.model.User;
import by.bookify.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
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
            return orderRepository.findByStatus(OrderStatus.valueOf(status));
        } catch (IllegalArgumentException e) {
            throw new StatusNotFoundException("Status with such a name doesn't exist.");
        }
    }

    @Override
    @Transactional
    public Order create(OrderCreateDto dto, String userEmail) {
        User user = (User) userService.loadUserByUsername(userEmail);
        Order order = toOrder(dto, user);
        updateBooks(dto);
        userService.updateOrderNum(user);
        statisticsService.incrementOrdersNum();
        Order newOrder = orderRepository.save(order);
        log.info("New order with id: {} has been created by user with id: {}", newOrder.getId(), user.getId());
        return newOrder;
    }

    @Override
    public void updateStatus(Long orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Такого заказа не существует."))
                .setStatus(orderStatus);
        orderRepository.save(order);
    }

    private Order toOrder(OrderCreateDto dto, User user) {
        return new Order()
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
    }

    private void updateBooks(OrderCreateDto dto) {
        dto.getBookIds().stream()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                .forEach((key, value) ->
                        bookService.updateBookQuantityParameters(key, value.intValue()));
    }
}
