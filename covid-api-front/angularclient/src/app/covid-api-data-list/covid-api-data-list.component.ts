import { Component, OnInit } from "@angular/core";
import { Covid19BrazilApiData } from "../data/covid19-brazil-api-data";
import { CovidApiService } from "../service/covid-api-service.service";

@Component({
  selector: "app-user-list",
  templateUrl: "./covid-api-data-list.component.html",
  styleUrls: ["./covid-api-data-list.component.css"],
})
export class CovidApiDataListComponent implements OnInit {
  dataList: Covid19BrazilApiData[];

  constructor(private covidApiService: CovidApiService) {}

  ngOnInit() {
    this.covidApiService.getCountriesListWithCases().subscribe((data) => {
      this.dataList = data;
    });
  }
}
