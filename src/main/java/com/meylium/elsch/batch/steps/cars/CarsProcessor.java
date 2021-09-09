package com.meylium.elsch.batch.steps.cars;

import com.meylium.elsch.model.Car;
import com.meylium.elsch.util.JobDto;
import org.springframework.batch.item.ItemProcessor;

public class CarsProcessor implements ItemProcessor<String[], JobDto> {
    @Override
    public JobDto process(String[] lineCsv) throws Exception {
        return toJobDto(lineCsv);
    }

    private JobDto toJobDto(String[] csvLine) {
        JobDto jobDto = new JobDto();
        try {
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
            jobDto.setData(car);
            jobDto.setStatus(true);
        } catch (Exception e) {
            jobDto.setErrorCode("PARSSING_ERROR");
            jobDto.setErrorMsg(e.getMessage());
        }
        return jobDto;
    }
}
