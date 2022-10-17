package com.kodilla.ecommercee.product;

import com.kodilla.ecommercee.exceptions.NoFoundGroupException;
import com.kodilla.ecommercee.exceptions.NoFoundProductException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    ProductService(final ProductRepository productRepository, final ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDto> getAllProducts(){
        return productMapper.mapToProductDtoList(productRepository.findAll());
    }
    public List<Product> getAllProductsById(final List<Long> productsId){
        return productRepository.findAllById(productsId);
    }
    public ProductDto getProductById(Long productId) throws NoFoundProductException {
        return productMapper.mapToProductDto(productRepository.findById(productId).orElseThrow(NoFoundProductException::new));
    }


    public void createProduct(ProductDto productDto) throws NoFoundGroupException {
        Product product = productMapper.mapToProduct(productDto);
        productRepository.save(product);
    }

    public void updateProduct(Long productId, ProductDto productDto) throws NoFoundProductException {
        if (!productRepository.existsById(productId))
            throw new NoFoundProductException();
        Product updatedProduct = productRepository.findById(productId).orElseThrow(NoFoundProductException::new);
        updatedProduct.setName(productDto.getName());
        updatedProduct.setDescription(productDto.getDescription());
        updatedProduct.setQuantity(productDto.getQuantity());
        updatedProduct.setPrice(productDto.getPrice());
        productRepository.save(updatedProduct);
    }

    public void deleteProductById(Long productId) throws NoFoundProductException {
        if (!productRepository.existsById(productId))
            throw new NoFoundProductException();
        productRepository.deleteById(productId);
    }
}
