import { TestBed } from "@angular/core/testing";

import { CovidApiServiceService } from "./covid-api-service.service";

describe("CovidApiServiceService", () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it("should be created", () => {
    const service: CovidApiServiceService = TestBed.get(CovidApiServiceService);
    expect(service).toBeTruthy();
  });
});
