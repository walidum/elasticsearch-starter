package com.meylium.elsch.controller;

import com.meylium.elsch.service.BatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("batch")
public class BatchController {
    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @GetMapping(path = "importusers")
    public ResponseEntity importUsers() {
        try {
            batchService.startJobImportUsersFromApi();
            return ResponseEntity.ok("Job started ...");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(path = "importCars")
    public ResponseEntity importCars() {
        try {
            batchService.startJobImportCarsFromCsv();
            return ResponseEntity.ok("Job started ...");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
