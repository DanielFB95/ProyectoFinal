import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

/** Servicio que prepara y ejecuta las peticiones */
@Injectable()
export class RestService {
    /** Cabeceras */
    private headers: HttpHeaders;
    /** Url de la API */
    private apiUrl: string;
    constructor(
        private http: HttpClient,
        private snackBar: MatSnackBar
    ) {
        this.headers = new HttpHeaders({ 'Authorization': `Bearer ${this.getToken()}` });
        this.apiUrl = environment.apiUrl;
    }
    /**
     * Función para obtener un token
     * @returns El token
     */
    getToken(): string {
        return localStorage.getItem('token');

    }
    /** Setea token */
    setToken(): void {
        this.headers = new HttpHeaders({ 'Authorization': `Bearer ${this.getToken()}` });

    }
    /**
     * Ejecuta la petición http
     * @param url Url
     * @param tipo Tipo de petición
     * @param valores Valores de la petición
     * @returns Observable con el resultado de la petición
     */
    peticionHttp(url: string, tipo: string, valores?, img?): Observable<any> {
        this.setToken();
        let peticion: Observable<any>;
        switch (tipo) {
            case 'get':
                peticion = this.http.get(this.apiUrl + url, { observe: 'body', headers: this.headers });
                break;
            case 'post':
                peticion = this.http.post(this.apiUrl + url, valores, { observe: 'body', headers: this.headers });
                break;
            case 'postFile':
                //this.headers.set("Content-Type", "multipart/form-data")
                const formData = new FormData();
                const json = JSON.stringify(valores);
                const blob = new Blob([json], {
                    type: 'application/json'
                  });

                formData.set('body', blob);  
                formData.append('file', img, img.name)


                peticion = this.http.post(this.apiUrl + url, formData, { observe: 'body', headers: this.headers });
                break;

            case 'put':
                peticion = this.http.put(this.apiUrl + url, valores, { observe: 'body', headers: this.headers });
                break;
            case 'delete':
                peticion = this.http.delete(this.apiUrl + url, { observe: 'body', headers: this.headers })
                break;

        }
        return new Observable(observer => {
            peticion.subscribe((response: any) => {
                if (!response?.error) {
                    observer.next(response);
                    observer.complete();
                } else {
                    observer.next(false);
                    observer.complete();
                }

            }, error => {
                this.gestionError(error)
                observer.error(error);
                observer.complete();
            })
        })
    }

    /**
     * Gestiona los errores de la petición
     * @param error Error a gestionar
     */
    gestionError(error: any) {
        if (error.status == 422) {
            this.snackBar.open('Hubo un error, comprueba que todos los campos son válidos.', "Cerrar", { duration: 4000, panelClass: 'snackBarWrong' });
        }
        else if (error.status == 422) {
            this.snackBar.open('Hubo un error, comprueba que todos los campos son válidos.', "Cerrar", { duration: 4000, panelClass: 'snackBarWrong' });
        }

    }



}