package com.meylium.elsch.controller;

import com.meylium.elsch.service.BatchService;
import com.meylium.elsch.util.JobDto;
import com.meylium.elsch.util.JobExecutionDto;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.NoSuchJobException;
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
    private final JobExplorer jobExplorer;

    public BatchController(BatchService batchService, JobExplorer jobExplorer) {
        this.batchService = batchService;
        this.jobExplorer = jobExplorer;
    }

    @PutMapping(path = "start/{name}")
    public ResponseEntity start(@PathVariable("name") final String name) {
        try {
            switch (name) {
                case "importFromRestApi":
                    batchService.startJobImportUsersFromApi();
                    break;
                case "importFromCsv":
                    batchService.startJobImportCarsFromCsv();
                    break;
            }
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
        List<JobDto> jobs = List.of(
                new JobDto("importFromRestApi", "Migrate Users", "This batch import data from rest api and store them as index in elasticsearch database"),
                new JobDto("importFromCsv", "Migrate cars", "This batch import data from rest csv file and store them as index in elasticsearch database")
        );
        return ResponseEntity.status(HttpStatus.OK).body(jobs);
    }

    @GetMapping(path = "one/{name}")
    public ResponseEntity<JobDto> one(@PathVariable(name = "name") final String name) {
        List<JobDto> jobs = List.of(
                new JobDto("importFromRestApi", "Migrate Users", "This batch import data from rest api and store them as index in elasticsearch database"),
                new JobDto("importFromCsv", "Migrate cars", "This batch import data from rest csv file and store them as index in elasticsearch database")
        );
        return ResponseEntity.ok(jobs.stream().filter(job -> job.getName().equals(name)).findFirst().orElse(null));
    }

}
