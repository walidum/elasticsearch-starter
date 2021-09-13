import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./modules/home/home.component";
import {UsersComponent} from "./modules/users/users.component";
import {JobsComponent} from "./modules/jobs/jobs.component";
import {NewJobComponent} from "./modules/jobs/new-job/new-job.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {path: 'home', component: HomeComponent},
  {path: 'users', component: UsersComponent},
  {path: 'jobs', component: JobsComponent},
  {path: 'jobs/new', component: NewJobComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
