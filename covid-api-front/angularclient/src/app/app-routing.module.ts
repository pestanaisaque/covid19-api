import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { CovidApiDataListComponent } from "./covid-api-data-list/covid-api-data-list.component";
// import { UserFormComponent } from './user-form/user-form.component';

const routes: Routes = [
  { path: "dataList", component: CovidApiDataListComponent },
  //   { path: 'adduser', component: UserFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
