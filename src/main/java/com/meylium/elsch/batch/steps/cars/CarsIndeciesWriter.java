package com.meylium.elsch.batch.steps.cars;

import com.meylium.elsch.batch.steps.common.CommonStep;
import com.meylium.elsch.model.Car;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class CarsIndeciesWriter extends CommonStep implements ItemWriter<Car> {
    @Override
    public void write(List<? extends Car> list) throws Exception {
        if (list == null || list.isEmpty()) return;
        bulkInsert(list, "cars");
    }
}
