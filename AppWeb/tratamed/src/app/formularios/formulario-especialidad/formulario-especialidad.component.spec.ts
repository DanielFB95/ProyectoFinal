import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioEspecialidadComponent } from './formulario-especialidad.component';

describe('FormularioEspecialidadComponent', () => {
  let component: FormularioEspecialidadComponent;
  let fixture: ComponentFixture<FormularioEspecialidadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormularioEspecialidadComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormularioEspecialidadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
