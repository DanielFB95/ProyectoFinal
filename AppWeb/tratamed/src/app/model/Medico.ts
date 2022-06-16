import { Especialidad } from "./Especialidad";
import { Usuario } from "./Usuario";

export interface Medico extends Usuario {
    numColegiado?:number;
    especialidad?:Especialidad;
}