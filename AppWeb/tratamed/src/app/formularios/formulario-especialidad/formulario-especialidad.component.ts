import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Especialidad } from 'src/app/model/Especialidad';

@Component({
  selector: 'app-formulario-especialidad',
  templateUrl: './formulario-especialidad.component.html',
  styleUrls: ['./formulario-especialidad.component.scss']
})
export class FormularioEspecialidadComponent implements OnInit {

    form: FormGroup;
    especialidad: Especialidad;
    mensaje: string = 'Creación de un especialidad';
    inspeccion: boolean = false;
    constructor(
        private dialogRef: MatDialogRef<FormularioEspecialidadComponent>,
        @Inject(MAT_DIALOG_DATA) data: { especialidad: Especialidad, inspeccion: boolean }
    ) {
        this.especialidad = data.especialidad;
        this.inspeccion = data.inspeccion;
        this.mensaje = this.especialidad ? 'Edición de un especialidad' : this.mensaje;
        this.mensaje = this.especialidad && this.inspeccion ? 'Inspección de un especialidad' : this.mensaje;
    }

    ngOnInit(): void {
        this.form = new FormGroup({
            nombre: new FormControl(this.especialidad?.nombre)
        });
        if (this.inspeccion) this.form.disable();

    }



    /** Confirmación del dialogo */
    save(): void {
        this.especialidad = {
            id: this.especialidad?.id,
            nombre: this.form.get('nombre').value
        };
        this.dialogRef.close(this.especialidad);
    }
    /** Cancelación del dialogo */
    close(): void {
        this.dialogRef.close(false);
    }

}
