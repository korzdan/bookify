package by.bookify.order.cotroller;

import by.bookify.order.model.Order;
import by.bookify.order.model.OrderCreateDto;
import by.bookify.order.model.OrderStatus;
import by.bookify.order.service.OrderService;
import by.bookify.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/my")
    public ResponseEntity<List<Order>> getUserOrder(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(orderService.findByUserId(user.getId()));
    }

    @GetMapping("/{status}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<List<Order>> findByStatus(@PathVariable String status) {
        return ResponseEntity.ok(orderService.findByStatus(status));
    }

    @PostMapping
    public ResponseEntity<String> createOrder(
            @AuthenticationPrincipal User user,
            @RequestBody OrderCreateDto dto) {
        orderService.create(dto, user.getEmail());
        return ResponseEntity.ok("Order is pending.");
    }

    @PostMapping("/deliver/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateOrderStatusToDelivered(@PathVariable Long orderId) {
        orderService.updateStatus(orderId, OrderStatus.DELIVERED);
        return ResponseEntity.ok("Order delivered.");
    }

    @PostMapping("/delivering/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateOrderStatusToDelivering(@PathVariable Long orderId) {
        orderService.updateStatus(orderId, OrderStatus.DELIVERING);
        return ResponseEntity.ok("Order delivering.");
    }
}
