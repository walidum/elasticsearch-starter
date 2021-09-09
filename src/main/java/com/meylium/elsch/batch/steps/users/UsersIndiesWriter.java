package com.meylium.elsch.batch.steps.users;

import com.meylium.elsch.batch.steps.common.CommonStep;
import com.meylium.elsch.model.User;
import com.meylium.elsch.util.Utils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.springframework.batch.item.ItemWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsersIndiesWriter extends CommonStep implements ItemWriter<User> {
    @Override
    public void write(List<? extends User> list) throws Exception {
        if (list == null || list.isEmpty()) return;
        bulkInsert(list);
    }

    private List<? extends User> bulkInsert(List<? extends User> users) throws Exception {
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


}
