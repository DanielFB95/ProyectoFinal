import { ValidatorFn, Validators } from '@angular/forms';
import { ElementoFormulario } from './ElementoFormulario';
/** Clase utilizada para la gestión de formularios genéricos y especificos, mostrandose luego en EditarGenericoComponent */
export class Formulario {
    /** Elemento que se trata en caso de ser de tipo edición o inspección el formulario */
    elemento: any;
    /** Lista de ElementoFormulario con el que trabaja */
    controles: ElementoFormulario[] = [];
    /** Modelo del elemento */
    modelo: string[] = [];
    /** Nombres visuales del elemento */
    visual: string[] = [];
    /** Elemento original, se utiliza en los formularios de edición para tener constancia del elemento original */
    elementoOriginal: any;
    /** Número de columnas que tendrá el formulario a mostrar */
    numeroColumnas: number = 1;
    constructor(
        public tipo: string,
        public mI: string[],
        public vI: string[],
        public titulo: string,
        public aceptar?: boolean,
    ) {
        this.mI.forEach(atributo => {
            this.addElemento(atributo, new ElementoFormulario(atributo, 'texto', this.tipo === 'inspeccion'));
            this.modelo.push(atributo);
        });
        this.vI.forEach(atributo => this.visual.push(atributo.replace('<br>', ' ')));
        this.elemento = {};
    }
    /**
     * Agrega una serie de validaciones a una serie de atributos
     *
     * @param validaciones Lista de Validators
     * @param atributos Lista de nombres de atributos de los campos
     */
    addValidacion(validaciones: ValidatorFn[], atributos?: string[]): void {
        atributos ?
            atributos.forEach(atributo => this.getElemento(atributo).control.setValidators(validaciones)) :
            this.modelo.forEach(atributo => this.getElemento(atributo).control.setValidators(validaciones))
    }
    /**
     * Agrega la validación Obligatorio a una serie de controles
     *
     * @param atributos
     */
    addRequired(atributos: string[]): void {
        atributos.forEach(atributo => this.getElemento(atributo).control.setValidators([Validators.required]));
    }

    /**
     * Recupera un elemento del formulario
     *
     * @param elemento Nombre del elemento a recuperar
     */
    getElemento(elemento: string): ElementoFormulario {
        return this.controles[elemento];
    }

    /**
     * Inserta un elemento personalizado en el formulario
     *
     * @param nombre nombre del control del elemento
     * @param elemento elemento a insertar
     */
    addElemento(nombre: string, elemento: ElementoFormulario): void {
        this.controles[nombre] = elemento;
    }
    /**
     * Inserta un elemento personalizado en el formulario
     *
     * @param nombre nombre del control del elemento
     * @param elemento elemento a insertar
     */
    addElementos(nombres: string[], elementos: ElementoFormulario[]): void {
        let i = 0;
        nombres.forEach(nombre => { this.controles[nombre] = elementos[i]; i++; });
    }

    /**
     * Deshabilita una lista de controles
     *
     * @param atributos Lista de nombre de controles
     */
    deshabilitaControles(atributos: string[]): void {
        atributos.forEach(atributo => this.getElemento(atributo).control.disable());
    }

    /**
     * Habilita una lista de controles
     *
     * @param atributos Lista de nombre de controles
     */
    habilitaControles(atributos: string[]): void {
        atributos.forEach(atributo => this.getElemento(atributo).control.enable());
    }

    /**
     * Cambia el tipo de control a una serie de controles
     *
     * @param tipo Tipo al que cambiar. EJ: 'fecha'
     * @param atributos Lista de nombres de controles
     */
    cambiarTipo(tipo: string, atributos: string[]): void {
        if (tipo === 'euro' || tipo === 'porcentaje' || tipo === 'orden') {
            atributos.forEach(atributo => { if (this.getElemento(atributo)) this.getElemento(atributo).tipo = 'numero'; this.getElemento(atributo).setFormatoNumero(tipo) });
        } else {
            atributos.forEach(atributo => { if (this.getElemento(atributo)) this.getElemento(atributo).tipo = tipo });
        }

    }

    /**
     * Cambia el tipo de formulario que es
     *
     * @param elemento Elemento asociado por si es de edición o inspección
     * @param tipo Tipo de formulario, puede ser creación, edición e inspección
     * @param titulo Titulo a mostrar para el formulario
     */
    cambiarTipoFormulario(elemento?: any, tipo?: string, titulo?: string): void {
        if (this.tipo === 'inspeccion' && tipo != 'inspeccion') {
            for (const value of Object.values(this.controles)) {
                value.disabled = false;
                value.control.enable();
            }
        }
        this.elemento = elemento;
        this.tipo = tipo ? tipo : this.tipo;
        this.titulo = titulo ? titulo : this.titulo;
    }

    /**
     * Para cambiar el formato que pueden tener los campos
     *
     * @param formato Formato a asignar. EJ: 'euro'
     * @param atributos Lista de nombres de controles al que modificarselo
     * @param mask Objeto opcional con parametros especificos de formato (suffix, decimal, thousands...)
     */
    setFormato(formato: string, atributos: string[], mask?: object) {
        atributos.forEach(atributo => this.getElemento(atributo).setFormatoNumero(formato, mask));
    }
    /**
     *  Para cambiar el tamaño de los campos, especificandose por número de orden
     * @param fxFlexes Lista de tamaños
     */
    setFxFlexes(fxFlexes: number[]) {
        for (let i = 0; i < fxFlexes.length; i++) {
            if (this.controles[this.modelo[i]]) this.controles[this.modelo[i]].fxFlex = fxFlexes[i];
        }
    }

}
