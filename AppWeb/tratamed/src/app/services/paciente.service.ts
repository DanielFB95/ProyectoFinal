import { Injectable } from '@angular/core';

import { RestService } from './rest.service';

import { Router } from '@angular/router';
import { Observable } from 'rxjs'
import { Paciente } from '../model/Paciente';
import { Receta } from '../model/Receta';
import { Medico } from '../model/Medico';

@Injectable()
export class PacienteService {
    private apiUrl: string = 'paciente/';
    constructor(
        public restService: RestService,
        private router: Router,
    ) {
    }

    recuperaPacientes(): Observable<Paciente[]> {
        const url: string = `${this.apiUrl}`;
        return new Observable(observer => {
            this.restService.peticionHttp(url, 'get').subscribe(resp => {
                console.log(resp)
                observer.next(resp ? resp.content : []);

                observer.complete();

            })
        });
    }

    recuperaRecetasPorPaciente(idPaciente: number): Observable<Receta[]> {
        const url: string = `paciente/recetas/${idPaciente}`;
        return new Observable(observer => {
            this.restService.peticionHttp(url, 'get').subscribe(resp => {
                observer.next(resp.content);

                observer.complete();

            })
        });
    }



    borrarPaciente(idMedico: number): Observable<boolean> {
        const url: string = `${this.apiUrl}${idMedico}`;
        return new Observable(observer => {
            this.restService.peticionHttp(url, 'delete').subscribe(response => {
                observer.next(response)
                observer.complete();
            });
        });
    }


    actualizaPaciente(paciente: Paciente): Observable<Paciente> {
        const url: string = `${this.apiUrl}${paciente.id}`;
        return new Observable(observer => {
            this.restService.peticionHttp(url, 'put', paciente).subscribe(resp => {
                observer.next(resp);
                observer.complete();
            });
        });
    }

    creaPaciente(paciente: Paciente, medico: Medico,img): Observable<Paciente> {
        const url: string = `user/${this.apiUrl}${medico.id}`;
        return new Observable(observer => {
            this.restService.peticionHttp(url, 'postFile', paciente,img).subscribe(resp => {
                observer.next(resp);
                observer.complete();
            });
        });
    }






}
