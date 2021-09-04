package com.meylium.elsch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.meylium.elsch.repo")
@ComponentScan(basePackages = { "com.meylium.elsch" })
public class ElasticsearchClientConfig extends AbstractElasticsearchConfiguration {

  @Value("elasticsearch.uri")
  private  String hostAndPort ;

  @Override
  @Bean
  public RestHighLevelClient elasticsearchClient() {

  final ClientConfiguration clientConfiguration =
    ClientConfiguration
      .builder()
      .connectedTo(hostAndPort)
      .build();
  
  return RestClients.create(clientConfiguration).rest();
  }
}
