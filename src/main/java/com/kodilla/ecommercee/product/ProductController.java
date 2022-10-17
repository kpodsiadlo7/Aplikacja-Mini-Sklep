package com.kodilla.ecommercee.product;

import com.kodilla.ecommercee.exceptions.NoFoundGroupException;
import com.kodilla.ecommercee.exceptions.NoFoundProductException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    ProductService productService;
    ProductRepository productRepository;
    ProductMapper productMapper;

    ProductController(final ProductService productService, final ProductRepository productRepository, final ProductMapper productMapper) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping(value = "{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) throws NoFoundProductException {
        return ResponseEntity.ok(productService.getProductById(productId));
    }
    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductDto productDto) throws NoFoundGroupException {
        productService.createProduct(productDto);
        return ResponseEntity.ok().build();
    }
    @PutMapping("{productId}")
    public ResponseEntity<Void> updateProduct(@RequestBody ProductDto productDto, @PathVariable Long productId) throws NoFoundProductException {
        productService.updateProduct(productId,productDto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) throws NoFoundProductException {
        productService.deleteProductById(productId);
        return ResponseEntity.ok().build();
    }
}
