package by.korzun.bookify.order.cotroller;

import by.korzun.bookify.order.model.CreateOrderDto;
import by.korzun.bookify.order.model.Order;
import by.korzun.bookify.order.service.OrderService;
import by.korzun.bookify.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
            @RequestBody CreateOrderDto dto)
    {
        orderService.createOrder(dto, user.getEmail());
        return ResponseEntity.ok("Заказ отправлен на обработку.");
    }

    @PostMapping("/deliver/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deliverOrder(@PathVariable Long orderId) {
        orderService.deliverOrderByOrderId(orderId);
        return ResponseEntity.ok("Заказ доставлен.");
    }

    @PostMapping("/delivering/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deliveringOrder(@PathVariable Long orderId) {
        orderService.deliveringOrderByOrderId(orderId);
        return ResponseEntity.ok("Заказ доставляется.");
    }
}
