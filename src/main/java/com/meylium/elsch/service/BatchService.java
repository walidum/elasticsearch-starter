package com.meylium.elsch.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BatchService {
    private final JobLauncher jobLauncher;
    private final Job jobImportUsersFromApi;


    public BatchService(JobLauncher jobLauncher, @Qualifier("batch1.job1") Job job) {
        this.jobLauncher = jobLauncher;
        this.jobImportUsersFromApi = job;
    }

    public void startJobImportUsersFromApi() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(jobImportUsersFromApi, jobParameters);
    }
}
