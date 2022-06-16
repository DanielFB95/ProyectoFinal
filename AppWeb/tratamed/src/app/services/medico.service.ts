import { Injectable, Inject, PLATFORM_ID } from '@angular/core';

import { RestService } from './rest.service';

import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Medico } from '../model/Medico';

@Injectable()
export class MedicoService {
    private apiUrl: string = 'medico/';
    constructor(
        public restService: RestService,
        private router: Router,
    ) {
    }

    recuperaMedicos(): Observable<Medico[]> {
        const url: string = `${this.apiUrl}`;
        return new Observable(observer => {
            this.restService.peticionHttp(url, 'get').subscribe(resp => {
                observer.next(resp ? resp.content : []);
                observer.complete();

            })
        });
    }


    borrarMedico(idMedico: number): Observable<boolean> {
        const url: string = `${this.apiUrl}${idMedico}`;
        return new Observable(observer => {
            this.restService.peticionHttp(url, 'delete').subscribe(response => {
                observer.next(response)
                observer.complete();

            });
        });
    }

    actualizaMedico(medico: Medico): Observable<Medico> {
        const url: string = `${this.apiUrl}${medico.id}`;
        return new Observable(observer => {
            this.restService.peticionHttp(url, 'put', medico).subscribe(resp => {
                observer.next(resp);
                observer.complete();
            });
        });
    }

    creaMedico(medico: Medico,avatar): Observable<Medico> {
        console.log(avatar)
        const url: string = `user/${this.apiUrl}`;
        return new Observable(observer => {
            this.restService.peticionHttp(url, 'postFile', medico,avatar).subscribe(resp => {
                observer.next(resp);
                observer.complete();
            });
        });
    }








}
