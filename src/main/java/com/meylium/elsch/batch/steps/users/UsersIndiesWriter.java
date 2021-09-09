package com.meylium.elsch.batch.steps.users;

import com.meylium.elsch.batch.steps.common.CommonStep;
import com.meylium.elsch.model.User;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class UsersIndiesWriter extends CommonStep implements ItemWriter<User> {
    @Override
    public void write(List<? extends User> list) throws Exception {
        if (list == null || list.isEmpty()) return;
        bulkInsert(list, "users");
    }


}
