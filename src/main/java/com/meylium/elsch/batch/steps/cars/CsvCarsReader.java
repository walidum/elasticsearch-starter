package com.meylium.elsch.batch.steps.cars;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvCarsReader implements ItemReader<String[]> {
    private final String fileName;
    private int nextUserIndex;

    private List<String[]> carsData;

    public CsvCarsReader(String fileName) {
        this.fileName = fileName;
        this.nextUserIndex = 0;

    }

    @Override
    public String[] read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (carsDataIsNotInitialized()) {
            this.carsData = populateCars(this.fileName);
        }
        String[] nextcarsLine = null;
        if (this.nextUserIndex < carsData.size()) {
            nextcarsLine = this.carsData.get(this.nextUserIndex);
            this.nextUserIndex++;
        } else {
            this.nextUserIndex = 0;
            carsData = null;

        }
        return nextcarsLine;
    }

    private boolean carsDataIsNotInitialized() {
        return this.carsData == null;
    }

    private ArrayList<String[]> populateCars(String fileName) throws IOException {
        InputStream inputStream = getFile(fileName);
        String cvsSplitBy = ";";
        boolean withHeader = true;
        ArrayList<String[]> toReturn = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));) {

            String line = withHeader ? br.readLine() : "";
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] carLine = line.split(cvsSplitBy);
                if (carLine.length != 9) {
                    continue;
                }
                toReturn.add(carLine);
            }
        } catch (IOException e) {

        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {
            }
        }
        return toReturn;
    }

    private InputStream getFile(String name) throws IOException {
        return new ClassPathResource(name).getInputStream();
    }
}
