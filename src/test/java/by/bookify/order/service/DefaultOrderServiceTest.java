package by.bookify.order.service;

import by.bookify.book.exception.BookNotFoundException;
import by.bookify.book.model.Book;
import by.bookify.book.model.BookLanguage;
import by.bookify.order.exception.StatusNotFoundException;
import by.bookify.order.model.OrderStatus;
import by.bookify.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultOrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private DefaultOrderService defaultOrderService;

    @Test
    void findAll_verifyCallOfFindAll_true() {
        defaultOrderService.findAll();
        verify(orderRepository).findAll();
    }

    @Test
    void findByUserId_verifyCallOfFindByUserId_true() {
        Long userId = 1L;
        defaultOrderService.findByUserId(userId);
        verify(orderRepository).findByUserId(userId);
    }

    @Test
    void findByStatus_verifyCallOfFindByStatus_true() {
        String status = "PENDING";
        defaultOrderService.findByStatus(status);
        verify(orderRepository).findByStatus(OrderStatus.valueOf(status));
    }

    @Test
    void findByStatus_statusDoesNotExist_thrownException() {
        String status = "PENDINGG";

        StatusNotFoundException actualException = assertThrows(StatusNotFoundException.class,
                () -> defaultOrderService.findByStatus(status));

        assertEquals("Status with such a name doesn't exist.", actualException.getMessage());
    }



}
