package com.kodilla.ecommercee.order;

import com.kodilla.ecommercee.cart.CartService;
import com.kodilla.ecommercee.exceptions.NoFoundCartException;
import com.kodilla.ecommercee.exceptions.NoFoundUserException;
import com.kodilla.ecommercee.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    private final UserService userService;
    private final CartService cartService;

    OrderMapper(final UserService userService, final CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    public Order mapToOrder(OrderDto orderDto) throws NoFoundUserException, NoFoundCartException {
        return new Order(
                orderDto.getShippingAddress(),
                orderDto.getShippingStatus(),
                userService.getUser(orderDto.getUserId()),
                cartService.getCart(orderDto.getCartId())
        );
    }

    public OrderDto mapToOrderDto(Order order){
        return new OrderDto(
                order.getId(),
                order.getCart().getId(),
                order.getUser().getId(),
                order.getDateOfOrder(),
                order.getShippingDate(),
                order.getShippingAddress(),
                order.getShippingStatus()
        );
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orderList){
        return orderList.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }
}
