package com.meylium.elsch.config;


import com.meylium.elsch.batch.listners.JobCompletionListener;
import com.meylium.elsch.batch.steps.Processor;
import com.meylium.elsch.batch.steps.Reader;
import com.meylium.elsch.batch.steps.Writer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BatchConfig {
    public final JobBuilderFactory jobBuilderFactory;
    public final StepBuilderFactory stepBuilderFactory;

    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }


    @Bean(name = "batch1.job1")
    public Job Job1() {
        return jobBuilderFactory.get("Job1")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(Step1())
                .end().build();
    }


    @Bean
    public Step Step1() {
        return stepBuilderFactory.get("Step1")
                .<String, String>chunk(1)
                .reader(new Reader())
                .processor(new Processor())
                .writer(new Writer())
                .build();
    }


    @Bean
    public JobExecutionListener listener() {
        return new JobCompletionListener();
    }

    @Bean
    public RestTemplate newRestTemplate() {
        return new RestTemplate();
    }
}
