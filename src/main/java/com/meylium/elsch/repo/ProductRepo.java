package com.meylium.elsch.repo;

import com.meylium.elsch.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepo extends ElasticsearchRepository<Product, String> {
}
