package com.meylium.elsch.batch.steps;

import com.meylium.elsch.model.User;
import com.meylium.elsch.util.Utils;
import org.apache.http.HttpHost;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.batch.item.ItemWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsersIndiesWriter implements ItemWriter<User> {
    @Override
    public void write(List<? extends User> list) throws Exception {
        if (list == null || list.isEmpty()) return;
        var result = bulkInsert(list);
        result.forEach(user -> System.out.println(user));
    }

    private List<? extends User> bulkInsert(List<? extends User> users) throws Exception {
        users.forEach(user -> System.out.println(user));
        BulkRequest bulkRequest = new BulkRequest();
        for (User user : users) {
            IndexRequest indexRequest = new IndexRequest("users");
            indexRequest.id(user.getId());
            indexRequest.source(Utils.introspect(user));
            bulkRequest.add(indexRequest);
        }

        try {
            BulkResponse bulk = client().bulk(bulkRequest, RequestOptions.DEFAULT);
            if (!bulk.hasFailures()) {
                return users;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    private RestHighLevelClient client() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        int port = 9200;
        String host = "127.0.0.1";
        RestClientBuilder builder = RestClient.builder(new HttpHost(host, port, "http"))
                .setHttpClientConfigCallback(httpClientBuilder ->
                        httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }
}
