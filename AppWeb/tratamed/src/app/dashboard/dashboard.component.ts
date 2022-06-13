import { Component, OnInit, ViewChild } from '@angular/core';
import { FormularioMedicoComponent } from '../formularios/formulario-medico/formulario-medico.component';
import { Medico } from '../model/Medico';
import { Paciente } from '../model/Paciente';
import { SharedService } from '../shared/shared.service';
import { forkJoin } from 'rxjs/internal/observable/forkJoin';
import { MedicoService } from '../services/medico.service';
import { PacienteService } from '../services/paciente.service';
import { FormularioPacienteComponent } from '../formularios/formulario-paciente/formulario-paciente.component';
import { TablaComponent } from '../shared/tables/tabla/tabla.component';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
    @ViewChild('tablaMedico', { static: false }) tMedico: TablaComponent;
    @ViewChild('tablaPaciente', { static: false }) tPaciente: TablaComponent;
    medicos: Medico[] = [
        // { id: 1, nombre: 'Nombre1', apellido: 'Apellido1', fechaNacimiento: '07/06/2022', telefono: 666777888, foto: '1', email: 'aa@gmail.com' },
        //    { id: 2, nombre: 'Nombre2', apellido: 'Apellido2', fechaNacimiento: '07/06/2022', telefono: 666777888, foto: '1', email: 'aa@gmail.com' }
    ];
    pacientes: Paciente[] = [
        //   { id: 1, nombre: 'Nombre1', apellido: 'Apellido1', fechaNacimiento: '07/06/2022', telefono: 666777888, foto: '1', email: 'aa@gmail.com' },
        //   { id: 2, nombre: 'Nombre2', apellido: 'Apellido2', fechaNacimiento: '07/06/2022', telefono: 666777888, foto: '1', email: 'aa@gmail.com' }
    ];

    columnas: string[] = ['Nombre', 'Apellidos', 'Fecha de nacimiento', 'Email', 'Teléfono'];
    modelo: string[] = ['nombre', 'apellidos', 'fechaNacimiento', 'email', 'telefono'];
    acciones: string[] = ['ver', 'editar', 'eliminarT'];
    medicoSeleccionado: Medico;
    constructor(
        private sharedService: SharedService,
        private medicoService: MedicoService,
        private pacienteService: PacienteService
    ) { }

    ngOnInit(): void {
        this.recuperarMedicosYPacientes();
    }

    recuperarMedicosYPacientes() {
        const info = forkJoin({
            medicos: this.medicoService.recuperaMedicos(),
            pacientes: this.pacienteService.recuperaPacientes()
        });
        info.subscribe(info => {
            console.log(info)
            this.medicos = info.medicos;
            this.pacientes = info.pacientes;
            if(this.tMedico) this.tMedico.refrescaTabla(this.medicos);
            if(this.tPaciente) this.tPaciente.refrescaTabla(this.pacientes);
        });
    }

    creaMedico() {
        this.sharedService.openGenericDialog(FormularioMedicoComponent, { medico: null, inspeccion: false }, '50vw').subscribe(dialog => dialog.subscribe(nuevoMedico => {
            if (nuevoMedico) {
                this.medicoService.creaMedico(nuevoMedico.medico,nuevoMedico.avatar).subscribe(medicoBBDD => {
                    if (this.tMedico) this.tMedico.addNuevoElemento(medicoBBDD);
                    else this.medicos = [medicoBBDD];
                });
            }
        }));
    }


    creaPaciente() {
        this.sharedService.openGenericDialog(FormularioPacienteComponent, { paciente: null, inspeccion: false }, '50vw').subscribe(dialog => dialog.subscribe(nuevoPaciente => {
            if (nuevoPaciente) {
                console.log(this.medicoSeleccionado)
                    this.pacienteService.creaPaciente(nuevoPaciente.paciente,this.medicoSeleccionado, nuevoPaciente.avatar).subscribe(pacienteBBDD => {
                        if (this.tPaciente) this.tPaciente.addNuevoElemento(pacienteBBDD);
                        else this.pacientes = [pacienteBBDD];
                    });
                

            }
        }));
    }

    onNotifyMedico(event: { accion: string, elemento: Medico }) {
        console.log(event)
        switch (event.accion) {
            case 'configurar':
                this.medicoSeleccionado = event.elemento;
                console.log(this.medicoSeleccionado)
                break;
            case 'ver':
                this.sharedService.openGenericDialog(FormularioMedicoComponent, { medico: event.elemento, inspeccion: true }, '50vw').subscribe(
                );
                break;
            case 'editar':
                this.sharedService.openGenericDialog(FormularioMedicoComponent, { medico: event.elemento, inspeccion: false }, '50vw').subscribe(dialog => dialog.subscribe(medicoActualizado => {
                    if (medicoActualizado) {
                        this.medicoService.actualizaMedico(medicoActualizado.medico).subscribe(nuevo => {
                            this.tMedico.sustituyeElemento(event.elemento, nuevo);
                        });
                    }

                }));
                break;
            case 'eliminarT':
                console.log(event)
                this.medicoService.borrarMedico(event.elemento.id).subscribe(() =>{
                    this.recuperarMedicosYPacientes();
                });
                break;
        }
    }

    onNotifyPaciente(event: { accion: string, elemento: Paciente }) {
        
        switch (event.accion) {
            case 'ver':
                this.sharedService.openGenericDialog(FormularioPacienteComponent, { paciente: event.elemento, inspeccion: true }, '50vw').subscribe();
                break;
            case 'editar':
                this.sharedService.openGenericDialog(FormularioPacienteComponent, { paciente: event.elemento, inspeccion: false }, '50vw').subscribe(dialog => dialog.subscribe(pacienteActualizado => {
                    if (pacienteActualizado) {
                        this.pacienteService.actualizaPaciente(pacienteActualizado.paciente).subscribe(nuevo => {
                            this.tPaciente.sustituyeElemento(event.elemento, nuevo);
                        });
                    }

                }));
                break;
            case 'eliminarT':
                this.pacienteService.borrarPaciente(event.elemento.id).subscribe();
                break;
        }
    }

}
