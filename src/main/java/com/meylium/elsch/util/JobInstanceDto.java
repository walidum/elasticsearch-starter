package com.meylium.elsch.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.batch.core.JobInstance;

@Getter
@Setter
@NoArgsConstructor
public class JobInstanceDto {
    private long id;
    private String jobName;

    public static JobInstanceDto toDto(JobInstance jobInstance) {
        JobInstanceDto toReturn = new JobInstanceDto();
        toReturn.setJobName(jobInstance.getJobName());
        toReturn.setId(jobInstance.getId());
        return toReturn;
    }
}
