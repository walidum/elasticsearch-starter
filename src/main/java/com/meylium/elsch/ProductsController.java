package com.meylium.elsch;

import com.meylium.elsch.model.Product;
import com.meylium.elsch.repo.ProductRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {
    private final ProductRepo productRepo;

    public ProductsController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @PostMapping(path = "/add")
    public ResponseEntity create(@RequestBody final Product product) {
        Product product1 = this.productRepo.save(product);
        return ResponseEntity.ok(product1);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<Iterable<Product>> all() {
        Iterable<Product> products = this.productRepo.findAll();
        return ResponseEntity.ok(products);
    }

}
