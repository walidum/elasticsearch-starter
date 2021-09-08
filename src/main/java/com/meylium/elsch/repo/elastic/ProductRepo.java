package com.meylium.elsch.repo.elastic;

import com.meylium.elsch.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepo extends ElasticsearchRepository<Product, String> {

}
