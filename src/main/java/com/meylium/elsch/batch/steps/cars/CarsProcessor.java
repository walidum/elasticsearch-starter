package com.meylium.elsch.batch.steps.cars;

import com.meylium.elsch.model.Car;
import org.springframework.batch.item.ItemProcessor;

public class CarsProcessor implements ItemProcessor<String[], Car> {
    @Override
    public Car process(String[] lineCsv) throws Exception {
        return toJobDto(lineCsv);
    }

    private Car toJobDto(String[] csvLine) throws Exception {
        Car car = new Car();
        car.setId(csvLine[0]);
        car.setName(csvLine[0]);
        car.setMpg(Double.parseDouble(csvLine[1]));
        car.setCylinders(Integer.parseInt(csvLine[2]));
        car.setDisplacement(Double.parseDouble(csvLine[3]));
        car.setHorsepower(Double.parseDouble(csvLine[4]));
        car.setWeight(Double.parseDouble(csvLine[5]));
        car.setAcceleration(Double.parseDouble(csvLine[6]));
        car.setMedel(Integer.parseInt(csvLine[7]));
        car.setOrigin(Car.Origin.valueOf(csvLine[8]));
        return car;
    }
}
