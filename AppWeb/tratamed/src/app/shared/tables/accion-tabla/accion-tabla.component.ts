import { ChangeDetectorRef, Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Accion } from '../../model/Accion';

/** Componente encargado de la lógica de acciones con condiciones */
@Component({
  selector: 'app-accion-tabla',
  templateUrl: './accion-tabla.component.html',
  styleUrls: ['./accion-tabla.component.scss']
})

export class AccionTablaComponent implements OnInit {
  /** Acción con la que se trabaja */
  @Input() accion: Accion;
  /** Elemento sobre el que se evalua */
  @Input() elemento: any;
  /** Evento de notificación de la acción */
  @Output() clicked: EventEmitter<any> = new EventEmitter<any>();
  /** Para saber si está deshabilitado o no */
  disabled = false;
  constructor(
    private cdRef: ChangeDetectorRef
  ) { }

  ngOnInit(): void {
    this.disabled = this.accion.disabled;

  }
  ngAfterViewChecked(): void {
    this.cdRef.detectChanges();
  }
  /** Envía la notificación */
  doAccion() {
    this.clicked.emit({});
  }





}
