package com.meylium.elsch.util;

import org.junit.jupiter.api.Test;

class UtilsTest {

    @Test
    void fetchProperties() {
    }

    @Test
    void readFileAsString() {
        try {
            String result = Utils.readFileAsString("ddd.json");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
