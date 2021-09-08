package com.meylium.elsch.controller;

import com.meylium.elsch.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductsControllerTest {

    @Autowired
    private ProductsController productsController;

    @Test
    void create() {
        Product product = new Product();
        product.setId("123");
        product.setCategory("cat");
        product.setPrice(20.1);
        product.setName("name");
        product.setQuantity(2);
        productsController.create(product);

        var iterator = productsController.all();
        var list = StreamSupport.stream(iterator.getBody().spliterator(), false).collect(Collectors.toList());

        Assertions.assertEquals(list.size(), 1);
        Assertions.assertEquals(list.get(0).getName(), "name");

        productsController.deleteAll();

        iterator = productsController.all();
        list = StreamSupport.stream(iterator.getBody().spliterator(), false).collect(Collectors.toList());

        Assertions.assertEquals(list.size(), 0);

    }

    @Test
    void all() {
    }
}
