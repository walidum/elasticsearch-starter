package com.meylium.elsch.controller;

import com.meylium.elsch.model.Product;
import com.meylium.elsch.service.ProductService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductsController.class)
public class StandaloneControllerTest {

    @MockBean
    ProductService productService;
    @Autowired
    MockMvc mockMvc;

    @Test
    void get_all_test() throws Exception {
        Product product = new Product();
        product.setName("test");
        product.setId("123");
        Mockito.when(productService.findAll()).thenReturn(Arrays.asList(product));

        mockMvc.perform(get("/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("test")))
                .andExpect(jsonPath("$[0].id", Matchers.is("123")));
    }
}
