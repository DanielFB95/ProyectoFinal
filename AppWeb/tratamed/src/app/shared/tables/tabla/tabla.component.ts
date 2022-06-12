import { AfterViewInit, Component, EventEmitter, Injectable, Input, OnInit, Output, ViewChild } from '@angular/core';

import { Accion } from '../../model/Accion';
import { SharedService } from '../../shared.service';

/** Componente de la Tabla encargada del listado y tratado de elementos */
@Component({
  selector: 'app-tabla',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.scss']
})
export class TablaComponent implements OnInit {
  /** Atributo con la colección de datos a mostrar */
  @Input() datos: any[] = [];
  /** Columnas a mostrar en la tabla */
  @Input() visual: string[];
  /** Columnas del modelo de la colección que recibe */
  @Input() modelo: string[];
  /** Acciones que deberán estar disponibles */
  @Input() acciones: string[];
  /** Permite que en la tabla, se puede hacer clic para seleccionar un elemento, y enviarlo */
  @Input() seleccionable: boolean;
  /** Elemento para notificar al componente que invoca la tabla */
  @Output() notify: EventEmitter<any> = new EventEmitter<any>();
  /** Elemento para notificar un requerimiento de tratamiento de datos después de paginar o filtrar */
  @Output() tratamiento: EventEmitter<any[]> = new EventEmitter<any[]>();
  /** Acciones listas para recorrerlas */
  accionesParsed: Accion[];
  /** Lista de acciones que es capaz de gestionar la tabla por si sola, si no está, enviará el evento y yasta */
  accionesAutoGestionadas: string[] = ['subir', 'bajar', 'eliminarT', 'eliminar', 'editarT'];
  /** Cadena de busqueda actualizada por la app-buscador */
  cadenaBusqueda: string;
  /** Datos a visualizar en la tabla */
  datosAMostrar: any[] = this.datos;
  /** Atributo auxiliar para tener en constancia que elemento ha sido seleccionado */
  elementoSeleccionado: any;

  constructor(
    private sharedService: SharedService
  ) { }

  ngOnInit(): void {
    this.preparaTabla();
  }


  /** Preparación inicial de herramientas necesarias para la tabla */
  preparaTabla(): void {
    this.datos = this.datos ? this.datos : [];
    this.datosAMostrar = this.datos.slice();
    this.accionesParsed = this.sharedService.parseAcciones(this.acciones);
    const index = this.accionesParsed.findIndex(accion => accion.funcion === 'configurar');

    if (index !== -1) {
      this.seleccionable = true;
      this.accionesParsed.splice(index, 1);
    }



  }
  /**
   * Lógica para el refresco de la tabla entera según datos que haya recibido
   *
   * @param datos Datos nuevos
   */
  refrescaTabla(datos: any): void {
    this.datos = datos ? datos : [];
    this.datosAMostrar = this.datos.slice();
  }
  /**
   * Evento de notificación de cuando se ha realizado una acción
   *
   * @param elemento Elemento sobre el cual se va a realizar la acción
   * @param accion Acción a realizar y devolver
   */
  doAccion(elemento: any, accion: string): void {
    if (!this.accionesAutoGestionadas.includes(accion)) {
      this.notify.emit({ accion, elemento });
    } else {
      switch (accion) {
        case 'eliminarT':
          this.sharedService.muestraConfirmacion('eliminarGenerico2', elemento, this.modelo[0], this.visual[0]).subscribe(accept => {
            if (accept) {
              this.datos.splice(this.datos.indexOf(elemento), 1);
              this.datosAMostrar.splice(this.datosAMostrar.indexOf(elemento), 1);
              this.notify.emit({ accion, elemento });
            }
          });
          break;
        case 'eliminar':
          this.sharedService.muestraConfirmacion('eliminarGenerico2', elemento, this.modelo[0], this.visual[0]).subscribe(accept => {
            if (accept) {
              this.notify.emit({ accion, elemento });
            }
          });
          break;
      }
    }
  }

  /**
   * Realiza la sustitución de un nuevo elemento. Se utiliza por ejemplo cuando no se usa EditorGenerico, o cuando hay que hacerle rollback a un elemento
   *
   * @param viejoElemento Elemento antiguo que buscar
   * @param nuevoElemento Elemento nuevo
   */
  sustituyeElemento(viejoElemento: any, nuevoElemento: any): void {
    const index: number = this.datos.indexOf(viejoElemento);
    if (index != -1) {
      this.datos[this.datos.indexOf(viejoElemento)] = nuevoElemento;
      this.datosAMostrar = this.datos.slice();
    } else {
      this.sharedService.openSnackBar('No se ha encontrado el elemento a sustituir', 3);
    }
  }

  /**
   * Lógica para añadir a la tabla un nuevo elemento. Si lleva clave primaria, no lo insertará si su clave está repetida
   *
   * @param elemento Elemento a añadir
   */
  addNuevoElemento(elemento: any): void {
      this.inserta(elemento);
  }
  /**
   * Borra un elemento de la tabla
   *
   * @param elemento Elemento a borrar
   */
  borraElemento(elemento: any): void {
    const result = this.datos.splice(this.datos.indexOf(elemento), 1);
    this.datosAMostrar.splice(this.datosAMostrar.indexOf(elemento), 1);
    if (result) this.sharedService.openSnackBar('Se ha eliminado correctamente', 3);
  }

  /**
   * Realiza una insercción
   *
   * @param elemento Elemento a insertar
   */
  inserta(elemento: any): void {
    this.datos.push(elemento);
    this.datosAMostrar = this.datos.slice();
    this.sharedService.openSnackBar('Se ha insertado correctamente', 3);
  }
  /**
   * Lógica de selección de un elemento
   *
   * @param elemento Elemento a seleccionar
   * @param atributo Por el que filtrar
   */
  seleccion(elemento: any, atributo?: string): void {
    if (this.seleccionable) {
      if (atributo) {
        const elementoEncontrado: any = this.datosAMostrar.find(dato => dato[atributo] === elemento[atributo]);
        this.elementoSeleccionado = elementoEncontrado;
      } else {
        this.elementoSeleccionado = this.elementoSeleccionado === elemento ? null : elemento;
      }
      this.notify.emit({ accion: 'configurar', elemento: this.elementoSeleccionado });
    }
  }

}