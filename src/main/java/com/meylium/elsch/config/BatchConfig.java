package com.meylium.elsch.config;


import com.meylium.elsch.batch.listners.JobCompletionListener;
import com.meylium.elsch.batch.steps.users.RestUsersReader;
import com.meylium.elsch.batch.steps.users.UsersDtosProcessor;
import com.meylium.elsch.batch.steps.users.UsersIndiesWriter;
import com.meylium.elsch.model.User;
import com.meylium.elsch.util.dtos.UserDto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BatchConfig {
    public final JobBuilderFactory jobBuilderFactory;
    public final StepBuilderFactory stepBuilderFactory;
    private final Environment env;

    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, Environment env) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.env = env;
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
                .<UserDto, User>chunk(1)
                .reader(new RestUsersReader(env.getProperty("users.testing.uri"), newRestTemplate()))
                .processor(new UsersDtosProcessor())
                .writer(new UsersIndiesWriter())
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
