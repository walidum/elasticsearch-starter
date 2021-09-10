import {Component, OnInit} from '@angular/core';
import {CommonService} from "../../services/common.service";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.scss']
})
export class JobsComponent implements OnInit {
  historyLines = []

  constructor(private service: CommonService) {
  }

  ngOnInit(): void {
  }

  loadData = () => {
    const batchName = 'importFromRestApi'
    this.service.list(environment.baseUri + "batch/history/" + batchName)
      .subscribe(res => {
        // this.historyLines = res;
      })
  }

}
