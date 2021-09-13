package com.meylium.elsch.controller;

import com.meylium.elsch.service.BatchService;
import com.meylium.elsch.util.JobDto;
import com.meylium.elsch.util.JobExecutionDto;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("batch")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class BatchController {
    private final BatchService batchService;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobExplorer jobExplorer;

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

    @GetMapping(path = "history/{name}")
    public ResponseEntity batchHistory(@PathVariable final String name) {
        int count = 0;
        try {
            count = jobExplorer.getJobInstanceCount(name);
        } catch (NoSuchJobException e) {
            return ResponseEntity.notFound().build();
        }
        List<JobInstance> result = jobExplorer.findJobInstancesByJobName(name, 0, count);
        List<JobExecutionDto> toReturn = new ArrayList<>();
        result.stream().forEach(i -> {
            List<JobExecutionDto> l = jobExplorer.getJobExecutions(i).stream()
                    .map(item -> JobExecutionDto.toDto(item))
                    .collect(Collectors.toList());
            if (l != null || !l.isEmpty())
                toReturn.addAll(l);
        });
        return ResponseEntity.ok(toReturn);
    }

    @GetMapping(path = "all")
    public ResponseEntity all() {
        List<JobDto> jobs = List.of(new JobDto("importFromRestApi", "Migrate Users"),
                new JobDto("importFromCsv", "Migrate cars"));
        return ResponseEntity.status(HttpStatus.OK).body(jobs);
    }

}
