import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CovidApiDataListComponent } from './covid-api-data-list.component';

describe('CovidApiDataListComponent', () => {
  let component: CovidApiDataListComponent;
  let fixture: ComponentFixture<CovidApiDataListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CovidApiDataListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CovidApiDataListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
