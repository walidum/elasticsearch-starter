package com.meylium.elsch.service;

import com.meylium.elsch.model.Product;
import com.meylium.elsch.repo.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    final private ProductRepo productRepo;

    ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Optional<Product> getProduct(String product_id) {
        return this.productRepo.findById(product_id);
    }

    public Product create(Product product) {
        return this.productRepo.save(product);
    }

    public void delete(String product_id) {
        this.productRepo.deleteById(product_id);
    }

    public void delete(Product product) {
        this.productRepo.delete(product);
    }

    public Product update(Product product) {
        return this.productRepo.save(product);
    }

}
