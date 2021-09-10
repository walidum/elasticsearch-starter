package com.meylium.elsch.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.batch.core.JobExecution;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class JobExecutionDto {
    private JobInstanceDto jobInstance;
    private List<StepExecutionDto> stepExecutions;
    private String status;
    private Date startTime;
    private Date createTime;
    private Date endTime;
    private Date lastUpdated;
    private String exitCode;
    private String exitDescription;

    public static JobExecutionDto toDto(JobExecution jobExecution) {
        JobExecutionDto toReturn = new JobExecutionDto();
        toReturn.setJobInstance(JobInstanceDto.toDto(jobExecution.getJobInstance()));
        toReturn.setCreateTime(jobExecution.getCreateTime());
        toReturn.setEndTime(jobExecution.getEndTime());
        toReturn.setExitCode(jobExecution.getExitStatus().getExitCode());
        toReturn.setExitDescription(jobExecution.getExitStatus().getExitDescription());
        toReturn.setLastUpdated(jobExecution.getLastUpdated());
        toReturn.setStartTime(jobExecution.getStartTime());
        toReturn.setStatus(jobExecution.getStatus().name());
        List<StepExecutionDto> list = jobExecution.getStepExecutions().stream().map(step -> StepExecutionDto.toDto(step)).collect(Collectors.toList());
        if (list != null || !list.isEmpty())
            toReturn.setStepExecutions(list);
        return toReturn;
    }
}
