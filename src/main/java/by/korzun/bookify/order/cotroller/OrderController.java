package by.korzun.bookify.order.cotroller;

import by.korzun.bookify.order.model.Order;
import by.korzun.bookify.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> findByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.findByUserId(userId));
    }

    @GetMapping("/{status}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<List<Order>> findByStatus(@PathVariable String status) {
        return ResponseEntity.ok(orderService.findByStatus(status));
    }

    @PostMapping
    public ResponseEntity<String> createOrder(
            @RequestParam String userEmail,
            @RequestBody List<Long> bookIds)
    {
        orderService.createOrder(bookIds, userEmail);
        return ResponseEntity.ok("Заказ отправлен на обработку.");
    }

    @PostMapping("/deliver/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deliverOrder(@PathVariable Long orderId) {
        orderService.deliverOrderByOrderId(orderId);
        return ResponseEntity.ok("Заказ доставлен.");
    }
}
