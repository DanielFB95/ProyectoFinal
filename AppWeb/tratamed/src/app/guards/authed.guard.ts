import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';
import { UserService } from '../services/user.service';
/** Comprueba si el usuario no está logado */
@Injectable()
export class AuthedGuard implements CanActivate {
    constructor(
        private userService: UserService
    ) { }
    /**
     * Comprueba si el usuario no esta logado
     * @returns True si no está logado
     */
    canActivate(): boolean {
        if (!this.userService.getUser()) {
            return true;
        } else {
            return false;
        }
    }
}