import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {CommonService} from "../../../services/common.service";
import {Job} from "../../../model/Job";
import {environment} from "../../../../environments/environment";

@Component({
  selector: 'app-new-job',
  templateUrl: './new-job.component.html',
  styleUrls: ['./new-job.component.scss']
})
export class NewJobComponent implements OnInit {
  name: string;
  job: Job = new Job('', '', '')

  constructor(private route: ActivatedRoute, private service: CommonService) {
    this.name = this.route.snapshot.params.name
  }

  ngOnInit(): void {
    this.loadJob()
  }

  loadJob = () => {
    this.service.one<Job>(environment.baseUri + 'batch/one/' + this.name).subscribe(res => {
      this.job = res
    })
  }

}
