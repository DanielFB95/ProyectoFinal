import { Injectable } from '@angular/core';

import { RestService } from './rest.service';

import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Medicamento } from '../model/Medicamento';

@Injectable()
export class MedicamentoService {
    private apiUrl: string = 'medicamento/';
    constructor(
        public restService: RestService
    ) {
    }

    recuperaMedicamentos(): Observable<Medicamento[]> {
        const url: string = `${this.apiUrl}`;
        return new Observable(observer => {
            this.restService.peticionHttp(url, 'get').subscribe(resp => {

                observer.next(resp ? resp.content : []);

                observer.complete();

            })
        });
    }


    borrarMedicamento(idMedicamento: number): Observable<boolean> {
        const url: string = `${this.apiUrl}${idMedicamento}`;
        return new Observable(observer => {
            this.restService.peticionHttp(url, 'delete').subscribe(response => {
                    observer.next(response)
                    observer.complete();
            });
        });
    }

    actualizaMedicamento(medicamento: Medicamento): Observable<Medicamento> {
        const url: string = `${this.apiUrl}${medicamento.id}`;
        return new Observable(observer => {
            this.restService.peticionHttp(url, 'put', medicamento).subscribe(resp => {
                observer.next(resp);
                observer.complete();
            });
        });
    }

    creaMedicamento(medicamento: Medicamento): Observable<Medicamento> {
        const url: string = `${this.apiUrl}`;
        return new Observable(observer => {
            this.restService.peticionHttp(url, 'post', medicamento).subscribe(resp => {

                observer.next(resp);

                observer.complete();
            });
        });
    }








}
