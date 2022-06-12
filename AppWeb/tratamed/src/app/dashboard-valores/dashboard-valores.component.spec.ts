import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardValoresComponent } from './dashboard-valores.component';

describe('DashboardValoresComponent', () => {
  let component: DashboardValoresComponent;
  let fixture: ComponentFixture<DashboardValoresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashboardValoresComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashboardValoresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
