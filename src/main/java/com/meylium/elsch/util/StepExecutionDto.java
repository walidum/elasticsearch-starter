package com.meylium.elsch.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.batch.core.StepExecution;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class StepExecutionDto {
    private String stepName;
    private String status;
    private int readCount;
    private int writeCount;
    private int commitCount;
    private int rollbackCount;
    private int readSkipCount;
    private int processSkipCount;
    private int writeSkipCount;
    private Date startTime;
    private Date endTime;
    private Date lastUpdated;
    private String exitCode;
    private String exitDescription;
    private boolean terminateOnly;
    private int filterCount;

    public static StepExecutionDto toDto(StepExecution stepExecution) {
        StepExecutionDto toReturn = new StepExecutionDto();
        toReturn.setExitCode(stepExecution.getExitStatus().getExitCode());
        toReturn.setExitDescription(stepExecution.getExitStatus().getExitDescription());
        toReturn.setCommitCount(stepExecution.getCommitCount());
        toReturn.setEndTime(stepExecution.getEndTime());
        toReturn.setFilterCount(stepExecution.getFilterCount());
        toReturn.setReadCount(stepExecution.getReadCount());
        toReturn.setReadSkipCount(stepExecution.getReadSkipCount());
        toReturn.setWriteCount(stepExecution.getWriteCount());
        toReturn.setLastUpdated(stepExecution.getLastUpdated());
        toReturn.setStatus(stepExecution.getStatus().name());
        return toReturn;
    }
}
