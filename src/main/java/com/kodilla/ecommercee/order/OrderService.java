package com.kodilla.ecommercee.order;

import com.kodilla.ecommercee.cart.Cart;
import com.kodilla.ecommercee.cart.CartRepository;
import com.kodilla.ecommercee.exceptions.NoFoundCartException;
import com.kodilla.ecommercee.exceptions.NoFoundOrderException;
import com.kodilla.ecommercee.exceptions.NoFoundUserException;
import com.kodilla.ecommercee.user.User;
import com.kodilla.ecommercee.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;

    OrderService(final CartRepository cartRepository, final OrderRepository orderRepository, final OrderMapper orderMapper, final UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.userRepository = userRepository;
    }
    public List<OrderDto> getAllOrders(){
        return orderMapper.mapToOrderDtoList(orderRepository.findAll());
    }
    public OrderDto getOrderDtoById(Long orderId) throws NoFoundOrderException {
        if (!orderRepository.existsById(orderId))
            throw new NoFoundOrderException();
        return orderMapper.mapToOrderDto(orderRepository.findById(orderId).orElseThrow(NoFoundOrderException::new));
    }
    public void createOrder(OrderDto orderDto) throws NoFoundUserException, NoFoundCartException {
        if (!cartRepository.existsById(orderDto.getCartId()))
            throw new NoFoundCartException();
        Order newOrder = orderMapper.mapToOrder(orderDto);
        orderRepository.save(newOrder);
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow(NoFoundCartException::new);
        cart.setOrder(newOrder);
        cartRepository.save(cart);
        User user = userRepository.findById(cart.getUser().getId()).orElseThrow(NoFoundUserException::new);
        user.setOrder(newOrder);
        userRepository.save(user);
    }
    public OrderDto updateOrder(Long orderId, OrderDto orderDto) throws NoFoundOrderException{
        if (!orderRepository.existsById(orderId))
            throw new NoFoundOrderException();
        Order updatedOrder = orderRepository.findById(orderId).orElseThrow(NoFoundOrderException::new);
        updatedOrder.setShippingAddress(updatedOrder.getShippingAddress());
        updatedOrder.setShippingStatus(orderDto.getShippingStatus());
        orderRepository.save(updatedOrder);
        return orderMapper.mapToOrderDto(updatedOrder);
    }
    public void deleteOrderById(Long orderId) throws NoFoundOrderException, NoFoundUserException, NoFoundCartException {
        if (!orderRepository.existsById(orderId))
            throw new NoFoundOrderException();
        User user = userRepository.findByOrderId(orderId).orElseThrow(NoFoundUserException::new);
        user.setOrder(null);
        userRepository.save(user);
        Cart cart = cartRepository.findById(user.getCart().getId()).orElseThrow(NoFoundCartException::new);
        cart.setOrder(null);
        cartRepository.save(cart);
        orderRepository.deleteById(orderId);
    }
}
