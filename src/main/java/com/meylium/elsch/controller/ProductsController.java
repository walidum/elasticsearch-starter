package com.meylium.elsch.controller;

import com.meylium.elsch.model.Product;
import com.meylium.elsch.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ProductsController {
    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity create(@RequestBody final Product product) {
        Product product1 = this.productService.create(product);
        return ResponseEntity.ok(product1);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<Iterable<Product>> all() {
        Iterable<Product> products = this.productService.findAll();
        return ResponseEntity.ok(products);
    }

    @DeleteMapping(path = "deleteall")
    public void deleteAll() {
        this.productService.deleteAll();
    }

}
