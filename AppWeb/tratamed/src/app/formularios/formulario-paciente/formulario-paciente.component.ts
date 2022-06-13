import { formatDate } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Paciente } from 'src/app/model/Paciente';
import { Receta } from 'src/app/model/Receta';
import { PacienteService } from 'src/app/services/paciente.service';

@Component({
    selector: 'app-formulario-paciente',
    templateUrl: './formulario-paciente.component.html',
    styleUrls: ['./formulario-paciente.component.scss']
})
export class FormularioPacienteComponent implements OnInit {
    form: FormGroup;
    paciente: Paciente;
    mensaje: string = 'Creación de un paciente';
    inspeccion: boolean = false;
    recetas: Receta[];
    /** Muestra primera contraseña */
    hidePassword: boolean = true;
    /** Muestra segunda contraseña */
    hidePassword2: boolean = true;
    avatar: any;
    constructor(
        private dialogRef: MatDialogRef<FormularioPacienteComponent>,
        @Inject(MAT_DIALOG_DATA) data: { paciente: Paciente, inspeccion: boolean },
        private pacienteService: PacienteService
    ) {
        this.paciente = data.paciente;
        this.inspeccion = data.inspeccion;
        this.mensaje = this.paciente ? 'Edición de un médico' : this.mensaje;
        this.mensaje = this.paciente && this.inspeccion ? 'Inspección de un paciente' : this.mensaje;
    }

    ngOnInit(): void {
        this.form = new FormGroup({
            nombre: new FormControl(this.paciente?.nombre),
            apellido: new FormControl(this.paciente?.apellidos),
            telefono: new FormControl(this.paciente?.telefono),
            email: new FormControl(this.paciente?.email),
            direccion: new FormControl(this.paciente?.direccion),
            dni: new FormControl(this.paciente?.dni),
            password: new FormControl(this.paciente?.password),
            password2: new FormControl(null),
            fechaNacimiento: new FormControl(this.paciente?.fechaNacimiento),
            observaciones: new FormControl(this.paciente?.observaciones),
            inputFotos: new FormControl(null)
        });

        if (this.paciente) {
            this.pacienteService.recuperaRecetasPorPaciente(this.paciente.id).subscribe(recetas => this.recetas = recetas);
        }

        if (this.inspeccion) this.form.disable();

    }

    /** Funciones relativas a la subida de ficheros
   * @param Evento de la imagen subida
   */
    imageUploaded(event: any): void {
        const rawImage: File = event.target.files[0];
        this.form.value.inputFotos = rawImage;
        this.avatar = rawImage;
    }


    /** Confirmación del dialogo */
    save(): void {
        let fecha: Date;
        let fechaFormateada;
        if (this.form.get('fechaNacimiento').value) {
            fecha = new Date(this.form.get('fechaNacimiento').value);
            fechaFormateada = formatDate(fecha, 'yyyy-MM-dd', 'es-es');
        }
        this.paciente = {
            id: this.paciente?.id,
            nombre: this.form.get('nombre').value,
            apellidos: this.form.get('apellido').value,
            email: this.form.get('email').value,
            direccion: this.form.get('direccion').value,
            dni: this.form.get('dni').value,
            avatar: this.form.get('inputFotos').value,
            fechaNacimiento: fechaFormateada,
            observaciones: this.form.get('observaciones').value,
            telefono: this.form.get('telefono').value,
            password: this.form.get('password').value,
            password2: this.form.get('password2').value
        };
        this.dialogRef.close({ paciente: this.paciente, avatar: this.avatar });
    }
    /** Cancelación del dialogo */
    close(): void {
        this.dialogRef.close(false);
    }

}
