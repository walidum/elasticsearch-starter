package com.meylium.elsch.batch.steps.cars;

import com.meylium.elsch.batch.steps.common.CommonStep;
import com.meylium.elsch.model.Car;
import com.meylium.elsch.util.Utils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.springframework.batch.item.ItemWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarsIndeciesWriter extends CommonStep implements ItemWriter<Car> {
    @Override
    public void write(List<? extends Car> list) throws Exception {
        if (list == null || list.isEmpty()) return;
        bulkInsert(list);
    }

    private List<? extends Car> bulkInsert(List<? extends Car> cars) throws Exception {
        BulkRequest bulkRequest = new BulkRequest();
        for (Car car : cars) {
            IndexRequest indexRequest = new IndexRequest("users");
            indexRequest.id(car.getId());
            indexRequest.source(Utils.introspect(car));
            bulkRequest.add(indexRequest);
        }

        try {
            BulkResponse bulk = client().bulk(bulkRequest, RequestOptions.DEFAULT);
            if (!bulk.hasFailures()) {
                return cars;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
