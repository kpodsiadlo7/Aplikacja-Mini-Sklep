package com.kodilla.ecommercee.order;

import com.kodilla.ecommercee.cart.Cart;
import com.kodilla.ecommercee.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ORDER_ID", unique = true)
    private Long id;

    @Column(name = "ORDER_DATE")
    private LocalDateTime dateOfOrder;

    @Column(name = "SHIPPING_DATE")
    private LocalDateTime shippingDate;

    @Column(name = "SHIPPING_ADDRESS")
    private String shippingAddress;

    @Column(name = "SHIPPING_STATUS")
    private String shippingStatus;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    public Order(final String shippingAddress, final String shippingStatus) {
        this.dateOfOrder = LocalDateTime.now();
        this.shippingDate = LocalDateTime.now();
        this.shippingAddress = shippingAddress;
        this.shippingStatus = shippingStatus;
    }

    Order(final String shippingAddress, final String shippingStatus, final User user, final Cart cart) {
        this.dateOfOrder = LocalDateTime.now();
        this.shippingDate = LocalDateTime.now().plusDays(5);
        this.shippingAddress = shippingAddress;
        this.shippingStatus = shippingStatus;
        this.user = user;
        this.cart = cart;
    }
}
