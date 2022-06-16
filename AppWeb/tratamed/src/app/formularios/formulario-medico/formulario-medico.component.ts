import { formatDate } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Especialidad } from 'src/app/model/Especialidad';
import { Medico } from 'src/app/model/Medico';
import { EspecialidadService } from 'src/app/services/especialidad.service';

@Component({
    selector: 'app-formulario-medico',
    templateUrl: './formulario-medico.component.html',
    styleUrls: ['./formulario-medico.component.scss']
})
export class FormularioMedicoComponent implements OnInit {
    form: FormGroup;
    medico: Medico;
    mensaje: string = 'Creación de un médico';
    inspeccion: boolean = false;
    $especialidades: Especialidad[];
    /** Muestra primera contraseña */
    hidePassword: boolean = true;
    /** Muestra segunda contraseña */
    hidePassword2: boolean = true;
    avatar:any;
    constructor(
        private dialogRef: MatDialogRef<FormularioMedicoComponent>,
        @Inject(MAT_DIALOG_DATA) data: { medico: Medico, inspeccion: boolean },
        private especialidadService: EspecialidadService
    ) {
        this.medico = data.medico;
        this.inspeccion = data.inspeccion;
        this.mensaje = this.medico ? 'Edición de un médico' : this.mensaje;
        this.mensaje = this.medico && this.inspeccion ? 'Inspección de un médico' : this.mensaje;
    }

    ngOnInit(): void {
        this.form = new FormGroup({
            nombre: new FormControl(this.medico?.nombre),
            apellido: new FormControl(this.medico?.apellidos),
            telefono: new FormControl(this.medico?.telefono),
            password: new FormControl(this.medico?.password),
            dni: new FormControl(this.medico?.dni),
            direccion: new FormControl(this.medico?.direccion),
            password2: new FormControl(),
            email: new FormControl(this.medico?.email),

            fechaNacimiento: new FormControl(this.medico?.fechaNacimiento ?? new Date()),
            numeroColegiado: new FormControl(this.medico?.numColegiado),
            especialidad: new FormControl(this.medico?.especialidad?.id),
            inputFotos: new FormControl(null)
        });
        if (this.inspeccion) this.form.disable();
        this.especialidadService.recuperaEspecialidades().subscribe(especialidades => {
            this.$especialidades = especialidades;


        });


    }

    /** Funciones relativas a la subida de ficheros
   * @param Evento de la imagen subida
   */
    imageUploaded(event: any): void {
        console.log(event.target.files)
        const rawImage: File = event.target.files[0];
        console.log(rawImage)
        this.avatar = rawImage;
       // console.log(this.form.value.inputFotos)
    }


    /** Confirmación del dialogo */
    save(): void {
        let fecha: Date;
        let fechaFormateada;
        if (this.form.get('fechaNacimiento').value) {
            fecha = new Date(this.form.get('fechaNacimiento').value);
            fechaFormateada = formatDate(fecha, 'yyyy-MM-dd', 'es-es');
        }

        this.medico = {
            id: this.medico?.id,
            nombre: this.form.get('nombre').value,
            apellidos: this.form.get('apellido').value,
            direccion: this.form.get('direccion').value,
            email: this.form.get('email').value,
            //avatar: this.form.get('inputFotos').value,
            fechaNacimiento: fechaFormateada ? fechaFormateada : null,
            numColegiado: this.form.get('numeroColegiado').value,
            dni: this.form.get('dni').value,
            especialidad: this.form.get('especialidad').value,
            telefono: this.form.get('telefono').value,
            password: this.form.get('password').value,
            password2: this.form.get('password2').value,
        };
        console.log(this.medico)
        this.dialogRef.close({medico:this.medico, avatar:this.avatar});
    }
    /** Cancelación del dialogo */
    close(): void {
        this.dialogRef.close(false);
    }

}
