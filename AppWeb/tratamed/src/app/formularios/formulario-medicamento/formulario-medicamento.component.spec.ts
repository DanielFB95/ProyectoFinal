import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioMedicamentoComponent } from './formulario-medicamento.component';

describe('FormularioMedicamentoComponent', () => {
  let component: FormularioMedicamentoComponent;
  let fixture: ComponentFixture<FormularioMedicamentoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormularioMedicamentoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormularioMedicamentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
