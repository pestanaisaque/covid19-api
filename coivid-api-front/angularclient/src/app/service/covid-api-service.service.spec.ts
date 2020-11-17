import { TestBed } from "@angular/core/testing";

import { CovidApiService } from "./covid-api-service.service";

describe("CovidApiServiceService", () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it("should be created", () => {
    const service: CovidApiService = TestBed.get(CovidApiService);
    expect(service).toBeTruthy();
  });
});
