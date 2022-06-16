import { BehaviorSubject } from 'rxjs';

/** Clase utilizada por las tablas para mostrar los iconos de las acciones y gestionar los eventos */
export class Accion {
    /** Catálogo de acciones predefinidas con su descripción e icono */
    static catalogo: object =
        {
            eliminar: { descripcion: 'Eliminar elemento', icono: 'clear' },
            eliminarT: { descripcion: 'Eliminar elemento', icono: 'clear' },
            configurar: { descripcion: 'Configurar elemento', icono: 'storage' },
            editar: { descripcion: 'Editar elemento', icono: 'edit' },
            editarT: { descripcion: 'Editar elemento', icono: 'edit' },
            ver: { descripcion: 'Inspeccionar elemento', icono: 'remove_red_eye' }
        };
    constructor(
        public funcion: string,
        public descripcion?: string,
        public icono?: string,
        public disabled?: boolean

    ) {
        this.descripcion = Accion.catalogo[this.funcion].descripcion;
        this.icono = Accion.catalogo[this.funcion].icono;
    }


}
