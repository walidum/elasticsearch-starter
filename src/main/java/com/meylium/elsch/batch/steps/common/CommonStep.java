package com.meylium.elsch.batch.steps.common;

import com.meylium.elsch.model.BaseIndex;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommonStep {

    protected <T extends BaseIndex> List<T> bulkInsert(List<T> items, String indexName) throws Exception {
        BulkRequest bulkRequest = new BulkRequest();
        for (BaseIndex item : items) {
            if (item.getId() == null || item.getId().isEmpty()) continue;
            IndexRequest indexRequest = new IndexRequest(indexName);
            indexRequest.id(item.getId());
            indexRequest.source(Utils.introspect((T) item));
            bulkRequest.add(indexRequest);
        }

        try {
            BulkResponse bulk = client().bulk(bulkRequest, RequestOptions.DEFAULT);
            if (!bulk.hasFailures()) {
                return items;
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
