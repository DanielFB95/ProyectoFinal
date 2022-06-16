import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Medicamento } from 'src/app/model/Medicamento';

@Component({
    selector: 'app-formulario-medicamento',
    templateUrl: './formulario-medicamento.component.html',
    styleUrls: ['./formulario-medicamento.component.scss']
})
export class FormularioMedicamentoComponent implements OnInit {

    form: FormGroup;
    medicamento: Medicamento;
    mensaje: string = 'Creación de un medicamento';
    inspeccion: boolean = false;
    constructor(
        private dialogRef: MatDialogRef<FormularioMedicamentoComponent>,
        @Inject(MAT_DIALOG_DATA) data: { medicamento: Medicamento, inspeccion: boolean }
    ) {
        this.medicamento = data.medicamento;
        this.inspeccion = data.inspeccion;
        this.mensaje = this.medicamento ? 'Edición de un medicamento' : this.mensaje;
        this.mensaje = this.medicamento && this.inspeccion ? 'Inspección de un medicamento' : this.mensaje;
    }

    ngOnInit(): void {
        this.form = new FormGroup({
            nombre: new FormControl(this.medicamento?.nombre),
            descripcion: new FormControl(this.medicamento?.descripcion),
        });
        if (this.inspeccion) this.form.disable();

    }



    /** Confirmación del dialogo */
    save(): void {
        this.medicamento = {
            id: this.medicamento?.id,
            nombre: this.form.get('nombre').value,
            descripcion: this.form.get('descripcion').value
        };
        this.dialogRef.close(this.medicamento);
    }
    /** Cancelación del dialogo */
    close(): void {
        this.dialogRef.close(false);
    }

}
