import { Component, OnInit, ViewChild } from '@angular/core';
import { forkJoin } from 'rxjs';
import { FormularioEspecialidadComponent } from '../formularios/formulario-especialidad/formulario-especialidad.component';
import { FormularioMedicamentoComponent } from '../formularios/formulario-medicamento/formulario-medicamento.component';
import { Especialidad } from '../model/Especialidad';
import { Medicamento } from '../model/Medicamento';
import { EspecialidadService } from '../services/especialidad.service';
import { MedicamentoService } from '../services/medicamento.service';
import { SharedService } from '../shared/shared.service';
import { TablaComponent } from '../shared/tables/tabla/tabla.component';

@Component({
    selector: 'app-dashboard-valores',
    templateUrl: './dashboard-valores.component.html',
    styleUrls: ['./dashboard-valores.component.scss']
})
export class DashboardValoresComponent implements OnInit {
    @ViewChild('tablaMedicamento', { static: false }) tMedicamento: TablaComponent;
    @ViewChild('tablaEspecialidad', { static: false }) tEspecialidad: TablaComponent;
    medicamentos: Medicamento[] = [
        //   { id: 1, nombre: 'Ibuprofeno' },
        //    { id: 2, nombre: 'Paracetamol' }
    ];
    especialidades: Especialidad[] = [
        //  { id: 1, nombre: 'Ginecologia' },
        //    { id: 2, nombre: 'Pediatria' }
    ];

    columnas: string[] = ['Id', 'Nombre'];
    modelo: string[] = ['id', 'nombre'];
    acciones: string[] = ['ver', 'editar', 'eliminarT'];
    constructor(
        private sharedService: SharedService,
        private medicamentoService: MedicamentoService,
        private especialidadService: EspecialidadService
    ) { }

    ngOnInit(): void {
        this.recuperarMedicamentosYEspecialidades();
    }

    recuperarMedicamentosYEspecialidades() {
        const info = forkJoin({
            medicamentos: this.medicamentoService.recuperaMedicamentos(),
            especialidades: this.especialidadService.recuperaEspecialidades()
        });
        info.subscribe(info => {
            console.log(info)
            this.medicamentos = info.medicamentos;
            this.especialidades = info.especialidades;
        });
    }

    creaMedicamento() {
        this.sharedService.openGenericDialog(FormularioMedicamentoComponent, { medicamento: null, inspeccion: false }, '50vw').subscribe(dialog => dialog.subscribe(nuevoMedicamento => {
            if (nuevoMedicamento) {
                this.medicamentoService.creaMedicamento(nuevoMedicamento).subscribe(medicamentoBBDD => {
                    if (this.tMedicamento) this.tMedicamento.addNuevoElemento(medicamentoBBDD);
                    else this.medicamentos = [medicamentoBBDD];
                });
            }
        }));
    }


    creaEspecialidad() {
        this.sharedService.openGenericDialog(FormularioEspecialidadComponent, { especialidad: null, inspeccion: false }, '50vw').subscribe(dialog => dialog.subscribe(nuevaEspecialidad => {
            if (nuevaEspecialidad) {
                this.especialidadService.creaEspecialidad(nuevaEspecialidad).subscribe(especialidadBBDD => {
                    if (this.tEspecialidad) this.tEspecialidad.addNuevoElemento(especialidadBBDD);
                    else this.especialidades = [especialidadBBDD];
                });
            }
        }));

    }

    onNotifyMedicamento(event: { accion: string, elemento: Medicamento }) {
        switch (event.accion) {
            case 'ver':
                this.sharedService.openGenericDialog(FormularioMedicamentoComponent, { medicamento: event.elemento, inspeccion: true }, '50vw').subscribe();
                break;
            case 'editar':
                this.sharedService.openGenericDialog(FormularioMedicamentoComponent, { medicamento: event.elemento, inspeccion: false }, '50vw').subscribe(dialog => dialog.subscribe(medicamentoActualizado => {
                    if (medicamentoActualizado) {
                        this.medicamentoService.actualizaMedicamento(medicamentoActualizado).subscribe(nuevo => {
                            this.tMedicamento.sustituyeElemento(event.elemento,nuevo);
                        });
                    }

                }));
                break;
            case 'eliminarT':
                this.medicamentoService.borrarMedicamento(event.elemento.id).subscribe();
                break;
        }
    }

    onNotifyEspecialidad(event: { accion: string, elemento: Especialidad }) {
        switch (event.accion) {
            case 'ver':
                this.sharedService.openGenericDialog(FormularioEspecialidadComponent, { especialidad: event.elemento, inspeccion: true }, '50vw').subscribe();
                break;
            case 'editar':
                this.sharedService.openGenericDialog(FormularioEspecialidadComponent, { especialidad: event.elemento, inspeccion: false }, '50vw').subscribe(dialog => dialog.subscribe(especialidadActualizada => {
                    if (especialidadActualizada) {
                        this.especialidadService.actualizaEspecialidad(especialidadActualizada).subscribe(esp=>{
                            this.tEspecialidad.sustituyeElemento(event.elemento,esp);
                        });
                        
                    }

                }));
                break;
            case 'eliminarT':
                this.especialidadService.borrarEspecialidad(event.elemento.id).subscribe();
                break;
        }
    }

}
