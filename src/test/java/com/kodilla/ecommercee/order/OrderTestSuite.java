package com.kodilla.ecommercee.order;

import com.kodilla.ecommercee.user.User;
import com.kodilla.ecommercee.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderTestSuite {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createOrder() {
        //Given
        Order order = new Order("dfdfg", "sdsdsds");
        //When
        orderRepository.save(order);
        //Then
        assertEquals(1, orderRepository.count());
        assertTrue(orderRepository.existsById(order.getId()));
        //CleanUp
        orderRepository.deleteById(order.getId());
    }

    @Test
    public void findAllOrders() {
        //Given
        Order Order = new Order();
        Order Order2 = new Order();
        Order Order3 = new Order();
        //When
        orderRepository.save(Order);
        orderRepository.save(Order2);
        orderRepository.save(Order3);
        //Then
        assertEquals(3, orderRepository.findAll().size());
        //Clean up
        orderRepository.deleteAll();
    }

    @Test
    public void findOrdersById() {
        //Given
        Order order = new Order();
        //When
        orderRepository.save(order);
        Optional<Order> testOrderId = orderRepository.findById(order.getId());
        //Then
        assertEquals(order.getId(), testOrderId.get().getId());
        //Clean up
        orderRepository.deleteAll();
    }

    @Test
    public void deleteOrderById() {
        //Given
        User user = new User("login", "password", "name", "surname", "address", "city", "666 666 666", "email@email.com");
        Order order = new Order("dfdfg", "sdsdsds");
        //When
        userRepository.save(user);
        orderRepository.save(order);
        //Then
        assertEquals(1, orderRepository.findAll().size());

        orderRepository.deleteById(order.getId());

        assertEquals(0, orderRepository.findAll().size());
        assertEquals(1, userRepository.findAll().size());
        //Clean up
        userRepository.deleteAll();
        orderRepository.deleteAll();
    }
}