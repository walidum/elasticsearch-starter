import {Component, OnInit} from '@angular/core';
import {CommonService} from "../../services/common.service";
import {environment} from "../../../environments/environment";
import {JobLine} from "../../model/JobLine";

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.scss']
})
export class JobsComponent implements OnInit {
  historyLines: JobLine[] = []

  constructor(private service: CommonService) {
  }

  ngOnInit(): void {
    this.loadData();
  }

  loadData = () => {
    const batchName = 'importFromRestApi'
    this.service.list<JobLine>(environment.baseUri + "batch/history/" + batchName)
      .subscribe((res) => {
        this.historyLines = res;
      })
  }

}
