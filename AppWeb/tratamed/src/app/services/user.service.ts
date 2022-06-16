import { Injectable, EventEmitter } from '@angular/core';

import { RestService } from './rest.service';

import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Usuario } from '../model/Usuario';

@Injectable()
export class UserService {
    /** Para el control de carga del spinner */
    public usuarioSeteado: EventEmitter<Usuario>;
    constructor(
        public restService: RestService,
        private router: Router,
    ) {
        this.usuarioSeteado = new EventEmitter();
    }

    /**
     * Lógica de autenticación
     * @param email Correo electrónico del usuario
     * @param password Contraseña del usuario
     * @returns Si el logueo ha sido correcto true/false
     */
    login(email: string, password: string): Observable<boolean> {
        const body = { email: email, password: password };
        const url: string = `auth/login`;

        return new Observable(observer => {
            this.restService.peticionHttp(url, 'post', body).subscribe(resp => {
                if (resp) {
                    if (resp.token) {
                        this.setUserAndToken(resp);
                        this.usuarioSeteado.emit(resp);
                        observer.next(true);
                    }
                } else {
                    observer.next(false);
                }
                observer.complete();
            })
        });
    }

    register(user: Usuario): Observable<Boolean> {
        const url: string = 'user';
        const body = {
            nombre: user.nombre,
            apellidos: user.apellidos,
            fechaNacimiento: user.fechaNacimiento,
            foto: user.fechaNacimiento,
            dni: user.dni,
            email: user.email,
            direccion: user.direccion,
            telefono: user.telefono,
            password: user.password
        };
        return new Observable(observer => {
            this.restService.peticionHttp(url, 'post', body).subscribe(resp => {
                console.log(resp)
                if (resp.user) {
                    this.setUserAndToken(resp);
                    observer.next(true)
                } else {
                    observer.next(false)
                }
            })
        });
    }






    /**
     * Guarda en cookies información del usuario
     * @param info Información del usuario
     */
    setUserAndToken(ínfo) {
        localStorage.setItem('profile', JSON.stringify(ínfo));
        localStorage.setItem('token', ínfo.token)
    }
    /**
     * Obtiene el usuario desde la cookie
     * @returns El usuario
     */
    getUser(): any {
        return JSON.parse(localStorage.getItem('profile'));
    }
    /** Borra las cookies y redirecciona al login */
    logout() {
        localStorage.removeItem('profile');
        localStorage.removeItem('token');
        this.router.navigate(['login']);

    }
    /** Petición para hacer ids */
    makeId(): string {
        return Math.random().toString(36).substring(2);
    }




}
