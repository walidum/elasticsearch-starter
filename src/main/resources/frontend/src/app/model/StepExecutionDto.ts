export interface StepExecutionDto {
  stepName: string;
  status: string;
  readCount: string;
  writeCount: string;
  commitCount: string;
  rollbackCount: string;
  readSkipCount: string;
  processSkipCount: string;
  writeSkipCount: string;
  startTime: string;
  endTime: string;
  lastUpd: string;
  exitCode: string;
  exitDescription: string;
  terminateOnly: string;
  filterCount: string;
}
