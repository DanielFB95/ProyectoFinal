import { Usuario } from "./Usuario";

export interface Paciente extends Usuario{

    observaciones?:string;
}