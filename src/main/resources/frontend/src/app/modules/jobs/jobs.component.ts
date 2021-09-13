import {Component, OnInit} from '@angular/core';
import {CommonService} from "../../services/common.service";
import {environment} from "../../../environments/environment";
import {JobLine} from "../../model/JobLine";
import {Job} from "../../model/Job";

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.scss']
})
export class JobsComponent implements OnInit {
  historyLines: JobLine[] = []
  jobs: Job[] = [];
  slectedJob: string = "";

  constructor(private service: CommonService) {
  }

  ngOnInit(): void {
    this.loadAvailibleJobs();
  }

  loadAvailibleJobs = () => {
    this.service.list<Job>(environment.baseUri + 'batch/all').subscribe(res => {
      this.jobs = res;
      if (this.jobs && this.jobs.length > 1) {
        this.slectedJob = this.jobs[0].name;
        this.loadJobHistory();
      }
    })
  }
  loadJobHistory = () => {
    this.service.list<JobLine>(environment.baseUri + "batch/history/" + this.slectedJob)
      .subscribe((res) => {
        this.historyLines = res;
      })
  }

}
