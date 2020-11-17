import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Covid19BrazilApiData } from "../data/covid19-brazil-api-data";
import { Observable } from "rxjs/Observable";

@Injectable()
export class CovidApiService {
  private uri: string;

  constructor(private http: HttpClient) {
    this.uri = "http://localhost:8080/api/countries";
  }

  public getCountriesListWithCases(): Observable<Covid19BrazilApiData[]> {
    return this.http.get<Covid19BrazilApiData[]>(this.uri);
  }

  public getCountryByName(
    data: Covid19BrazilApiData
  ): Observable<Covid19BrazilApiData> {
    return this.http.get<Covid19BrazilApiData>(`${this.uri}/${data.country}`);
  }

  public saveCountryInFavorites(data: Covid19BrazilApiData) {
    return this.http.post<Covid19BrazilApiData>(this.uri, data);
  }

  public updateCountryInFavorites(data: Covid19BrazilApiData) {
    return this.http.put<Covid19BrazilApiData>(
      `${this.uri}/${data.country}`,
      data.country
    );
  }

  public deleteCountryFromFavorites(data: Covid19BrazilApiData) {
    return this.http.delete<Covid19BrazilApiData>(
      `${this.uri}/${data.country}`
    );
  }
}
