package com.kodilla.ecommercee.cart;

import com.kodilla.ecommercee.exceptions.NoFoundOrderException;
import com.kodilla.ecommercee.exceptions.NoFoundUserException;
import com.kodilla.ecommercee.order.OrderRepository;
import com.kodilla.ecommercee.product.Product;
import com.kodilla.ecommercee.product.ProductService;
import com.kodilla.ecommercee.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartMapper {

    private final UserService userService;
    private final ProductService productService;
    private final OrderRepository orderRepository;

    CartMapper(final UserService userService, final ProductService productService, final OrderRepository orderRepository) {
        this.userService = userService;
        this.productService = productService;
        this.orderRepository = orderRepository;
    }

    public Cart mapToCart(CartDto cartDto) throws NoFoundUserException, NoFoundOrderException {
        return new Cart(
                userService.getUser(cartDto.getUserId()),
                orderRepository.findById(cartDto.getOrderId()).orElseThrow(NoFoundOrderException::new),
                productService.getAllProductsById(cartDto.getProductsId())
        );
    }

    public CartDto mapToCartDto(Cart cart){
        return new CartDto(
                cart.getId(),
                cart.getUser().getId(),
                cart.getOrder().getId(),
                cart.getProducts().stream().map(Product::getId).collect(Collectors.toList())
        );
    }

    public List<CartDto> mapToCartDtoList(final List<Cart> cartList) {
        return cartList.stream()
                .map(this::mapToCartDto)
                .collect(Collectors.toList());
    }
}