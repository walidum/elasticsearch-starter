import {Component, OnInit} from '@angular/core';
import {CommonService} from "../../services/common.service";
import {environment} from "../../../environments/environment";
import {JobLine} from "../../model/JobLine";
import {Job} from "../../model/Job";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.scss']
})
export class JobsComponent implements OnInit {
  historyLines: JobLine[] = []
  jobs: Job[] = [];
  slectedJob: string = "";
  form: FormGroup

  constructor(private service: CommonService, private fb: FormBuilder) {
    this.form = this.fb.group({
      name: []
    })
  }

  ngOnInit(): void {

    this.loadAvailibleJobs();
  }

  loadAvailibleJobs = () => {
    this.service.list<Job>(environment.baseUri + 'batch/all').subscribe(res => {
      this.jobs = res;
      if (this.jobs && this.jobs.length > 1) {
        this.slectedJob = this.jobs[0].name;
        this.form.patchValue({name: this.slectedJob})
        this.loadJobHistory();
      }
    })
  }
  loadJobHistory = () => {
    this.slectedJob = this.form.get("name")?.value
    this.service.list<JobLine>(environment.baseUri + "batch/history/" + this.slectedJob)
      .subscribe((res) => {
        this.historyLines = res;
      })
  }

}
