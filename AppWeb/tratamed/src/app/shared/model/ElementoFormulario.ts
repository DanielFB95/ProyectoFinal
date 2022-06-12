
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
/** Clase utilizada por la clase Formulario para tener una lista de controles y gestionarlos */
export class ElementoFormulario {
  /** FormControl que tendrá vinculado el elemento */
  control: FormControl;
  /** Formato número que podrá tener */
  formatoNumero: object = { suffix: '' };
  /** Flex que podrá tener asociado para establecer un tamaño al campo, si no lo tiene, coge el por defecto del formulario */
  fxFlex: number = null;
  constructor(
    public nombre: string,
    public tipo: string,
    public disabled?: boolean,
    public listado?: Observable<any> | any[],
    public label?: string,
    public value?: string,
    public secondLabel?: string,
    public multiple?: boolean
  ) {
    this.control = new FormControl({ value: null, disabled: this.disabled });
    this.multiple = multiple === true;
  }
  /**
   * Función que fija el tipo de formato númerico para mostrar
   *
   * @param tipo Tipo a elegir
   * @param mask Objeto con un formato personalizado
   */
  setFormatoNumero(tipo: string, mask?: object): void {
    switch (tipo) {
      case 'euro':
        this.formatoNumero = { suffix: '€' };
        break;
      case 'porcentaje':
        this.formatoNumero = { suffix: '%' };
        break;
      case 'plano':
        this.formatoNumero = { thousands: '' };
        break;
      case 'orden':
        this.formatoNumero = { suffix: 'º' };
        break;
    }
    if (mask) {
      Object.entries(mask).forEach(([clave, valor]) => {
        this.formatoNumero[clave] = valor;
      });
    }
  }
}