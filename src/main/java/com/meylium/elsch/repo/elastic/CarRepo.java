package com.meylium.elsch.repo.elastic;

import com.meylium.elsch.model.Car;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CarRepo extends ElasticsearchRepository<Car, String> {
}
