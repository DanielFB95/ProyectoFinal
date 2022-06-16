import { Injectable } from '@angular/core';

import { RestService } from './rest.service';

import { Router } from '@angular/router';
import { Observable } from 'rxjs'
import { Especialidad } from '../model/Especialidad';

@Injectable()
export class EspecialidadService {
    private apiUrl: string = 'especialidad/';
    constructor(
        public restService: RestService,
        private router: Router,
    ) {
    }

    recuperaEspecialidades(): Observable<Especialidad[]> {
        const url: string = `${this.apiUrl}`;
        return new Observable(observer => {
            this.restService.peticionHttp(url, 'get').subscribe(resp => {
                observer.next(resp ? resp.content : []);
                observer.complete();

            })
        });
    }



    borrarEspecialidad(idEspecialidad: number): Observable<boolean> {
        const url: string = `${this.apiUrl}${idEspecialidad}`;
        return new Observable(observer => {
            this.restService.peticionHttp(url, 'delete').subscribe(response => {
                observer.next(response);
                observer.complete();

            });
        });
    }


    actualizaEspecialidad(especialidad: Especialidad): Observable<Especialidad> {
        const url: string = `${this.apiUrl}${especialidad.id}`;
        return new Observable(observer => {
            this.restService.peticionHttp(url, 'put', especialidad).subscribe(resp => {
                observer.next(resp);
                observer.complete();
            });
        });
    }

    creaEspecialidad(especialidad: Especialidad): Observable<Especialidad> {
        const url: string = `${this.apiUrl}`;
        return new Observable(observer => {
            this.restService.peticionHttp(url, 'post', especialidad).subscribe(resp => {
                observer.next(resp);
                observer.complete();
            });
        });
    }






}
