import {JobInstanceDto} from "./JobInstanceDto";
import {StepExecutionDto} from "./StepExecutionDto";

export interface JobLine {
  jobInstance: JobInstanceDto
  stepExecutions: StepExecutionDto[];
  status: string;
  startTime: string;
  createTime: string;
  endTime: string;
  lastUpdated: string;
  exitCode: string;
  exitDescription: string;
}
