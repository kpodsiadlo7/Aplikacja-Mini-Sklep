package com.kodilla.ecommercee.order;

import com.kodilla.ecommercee.exceptions.NoFoundCartException;
import com.kodilla.ecommercee.exceptions.NoFoundOrderException;
import com.kodilla.ecommercee.exceptions.NoFoundUserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    private final OrderService orderService;

    OrderController(final OrderService orderService, final OrderRepository orderRepository) {
        this.orderService = orderService;
    }

    /**
     * METHOD TO IMPLEMENT
     *
     * GET ORDER
     */

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    @GetMapping(value = "{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long orderId) throws NoFoundOrderException {
        return ResponseEntity.ok(orderService.getOrderDtoById(orderId));
    }
    @PutMapping("{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto, @PathVariable Long orderId) throws NoFoundOrderException, NoFoundUserException {
        return ResponseEntity.ok(orderService.updateOrder(orderId,orderDto));
    }
    @DeleteMapping(value = "{orderId}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long orderId) throws NoFoundOrderException, NoFoundUserException, NoFoundCartException {
        orderService.deleteOrderById(orderId);
        return ResponseEntity.ok().build();
    }
}
