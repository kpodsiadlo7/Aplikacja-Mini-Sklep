package com.kodilla.ecommercee.product;

import com.kodilla.ecommercee.cart.Cart;
import com.kodilla.ecommercee.exceptions.NoFoundGroupException;
import com.kodilla.ecommercee.group.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMapper {

    private final GroupService groupService;

    ProductMapper(final GroupService groupService) {
        this.groupService = groupService;
    }

    public Product mapToProduct(final ProductDto productDto) throws NoFoundGroupException {
        return new Product(
                productDto.getName(),
                productDto.getDescription(),
                productDto.getQuantity(),
                productDto.getPrice(),
                groupService.getGroup(productDto.getGroupId())
        );
    }

    public ProductDto mapToProductDto(final Product product){
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice(),
                product.getCarts().stream().map(Cart::getId).collect(Collectors.toList()),
                product.getGroup().getId()
        );
    }
    public List<ProductDto> mapToProductDtoList(final List<Product> productList){
        return productList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}