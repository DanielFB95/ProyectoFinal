import { Medicamento } from "./Medicamento";
import { Medico } from "./Medico";
import { Paciente } from "./Paciente";

export interface Receta{
    id:number;
    medico:Medico;
    paciente:Paciente;
    medicamento:Medicamento;
    fechaInicio:Date;
    fechaFin:Date,
    diasDeTomas:string[];
    momentosDeToma:string[];
}