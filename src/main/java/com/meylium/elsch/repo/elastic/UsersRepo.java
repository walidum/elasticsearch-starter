package com.meylium.elsch.repo.elastic;

import com.meylium.elsch.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UsersRepo extends ElasticsearchRepository<User, String> {
}
