import { Component, AfterViewInit, Injector, Input, ChangeDetectorRef } from '@angular/core';
import { MatFormField, MatFormFieldControl } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';

/** Directiva para mostrar mensajes de error en los inputs */
@Component({
  selector: '[matErrorMessages]',
  template: '{{ error }}'
})
export class MatErrorMessagesDirective implements AfterViewInit {
  /** Error a mostrar */
  error = '';
  /** Referencia del input */
  inputRef: MatFormFieldControl<MatInput>;
  /** Custom mensaje */
  @Input() customPattern: string;
  constructor(
    private _inj: Injector,
    private cdRef: ChangeDetectorRef
  ) { }

  // Setup all initial tooling
  ngAfterViewInit() {
    // grab reference to MatFormField directive, where form control is accessible.
    let container = this._inj.get(MatFormField);
    this.inputRef = container._control;

    // sub to the control's status stream
    this.inputRef.ngControl.statusChanges.subscribe(this.updateErrors);
    this.updateErrors("INVALID");
    this.cdRef.detectChanges();

  }

  // This grabs a single active error instead of multiple.
  private updateErrors = (state: 'VALID' | 'INVALID') => {
    if (state === 'INVALID') {
      let controlErrors = this.inputRef.ngControl.errors;
      const firstError = controlErrors ? Object.keys(controlErrors)[0] : null;
      if (firstError === 'required')
        this.error = 'Este campo es obligatorio.';

      if (firstError === 'minlength')
        this.error = 'Este campo debe tener al menos ' + controlErrors['minlength'].requiredLength + ' caracteres.';

      if (firstError === 'maxlength')
        this.error = 'Este campo no debe superar los ' + controlErrors['maxlength'].requiredLength + ' caracteres.';

      if (firstError === 'email')
        this.error = 'El correo introducido no es valido, por favor, reviselo.';

      if (firstError === 'pattern')
        this.error = this.customPattern ? this.customPattern : 'El campo introducido no es correcto.';

      if (firstError === 'usedEmail')
        this.error = 'El correo electronico ya esta siendo usado'

      if (firstError === 'usedNickname')
        this.error = 'El nombre de usuario ya esta siendo usado'

      if (firstError === 'error from my own custom validator')
        this.error = 'You get the point.';
      // ..... 
    }
  }
}