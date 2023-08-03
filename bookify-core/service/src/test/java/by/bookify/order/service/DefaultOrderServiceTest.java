package by.bookify.order.service;

import by.bookify.book.service.BookService;
import by.bookify.domain.book.model.Book;
import by.bookify.domain.order.dto.OrderCreateDto;
import by.bookify.domain.order.exception.OrderNotFoundException;
import by.bookify.domain.order.exception.StatusNotFoundException;
import by.bookify.domain.order.model.Order;
import by.bookify.domain.order.model.OrderStatus;
import by.bookify.domain.user.model.User;
import by.bookify.order.repository.OrderRepository;
import by.bookify.statistics.service.StatisticsService;
import by.bookify.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultOrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserService userService;
    @Mock
    private BookService bookService;
    @Mock
    private StatisticsService statisticsService;
    @InjectMocks
    private DefaultOrderService orderService;

    @Test
    void findAll_verifyCallOfFindAll_true() {
        orderService.findAll();

        verify(orderRepository).findAll();
    }

    @Test
    void findByUserId_verifyCallOfFindByUserId_true() {
        Long userId = 1L;
        orderService.findByUserId(userId);

        verify(orderRepository).findByUserId(userId);
    }

    @Test
    void findByStatus_verifyCallOfFindByStatus_true() {
        String status = "PENDING";
        orderService.findByStatus(status);

        verify(orderRepository).findByStatus(OrderStatus.valueOf(status));
    }

    @Test
    void findByStatus_statusDoesNotExist_thrownException() {
        String status = "PENDINGG";

        StatusNotFoundException actualException = assertThrows(
                StatusNotFoundException.class,
                () -> orderService.findByStatus(status));
        assertEquals("Status with such a name doesn't exist.", actualException.getMessage());
    }

    @Test
    void createOrder() {
        String userEmail = "username";
        OrderCreateDto dto = new OrderCreateDto()
                .setBookIds(List.of(1L));
        User user = new User();
        Order expectedOrder = new Order()
                .setUser(user)
                .setBooks(List.of(new Book()))
                .setStatus(OrderStatus.PENDING);

        when(userService.loadUserByUsername(userEmail))
                .thenReturn(user);
        when(bookService.findById(1L))
                .thenReturn(new Book());
        when(orderRepository.save(any()))
                .thenReturn(expectedOrder);
        Order actualOrder = orderService.create(dto, userEmail);

        assertEquals(expectedOrder.getBooks().size(), actualOrder.getBooks().size());
        assertEquals(expectedOrder.getStatus(), actualOrder.getStatus());
        assertEquals(expectedOrder.getUser(), actualOrder.getUser());
    }

    @Test
    void updateStatus() {
        Long orderId = 1L;
        Order order = new Order()
                .setStatus(OrderStatus.PENDING);
        Order expectedOrder = new Order()
                .setStatus(OrderStatus.DELIVERING);

        when(orderRepository.findById(orderId))
                .thenReturn(Optional.of(order));
        when(orderRepository.save(any()))
                .thenReturn(expectedOrder);
        Order actualOrder = orderService.updateStatus(orderId, OrderStatus.DELIVERING);

        assertEquals(expectedOrder.getStatus(), actualOrder.getStatus());
    }

    @Test
    void updateStatus_throwOrderNotFoundException() {
        Long orderId = 1L;

        when(orderRepository.findById(orderId))
                .thenReturn(Optional.empty());

        assertThrows(
                OrderNotFoundException.class,
                () -> orderService.updateStatus(orderId, OrderStatus.DELIVERING)
        );
    }
}
