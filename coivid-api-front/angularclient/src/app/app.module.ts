import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { AppRoutingModule } from "./app-routing.module";
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { AppComponent } from "./app.component";
import { CovidApiDataListComponent } from "./covid-api-data-list/covid-api-data-list.component";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";

import { CovidApiService } from "./covid-api-service.service";

@NgModule({
  declarations: [AppComponent, CovidApiDataListComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    FontAwesomeModule,
  ],
  providers: [CovidApiService],
  bootstrap: [AppComponent],
})
export class AppModule {}
