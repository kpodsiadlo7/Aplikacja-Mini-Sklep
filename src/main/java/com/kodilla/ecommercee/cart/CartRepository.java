package com.kodilla.ecommercee.cart;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface CartRepository extends CrudRepository<Cart,Long> {

    @Override
    Optional<Cart> findById(Long id);

    @Override
    List<Cart> findAll();

    @Override
    void deleteById(Long id);

    @Override
    boolean existsById(Long cartId);

    @Override
    List<Cart> findAllById(Iterable<Long> longs);
}