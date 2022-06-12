import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from '../services/user.service';
/** Guard que comprueba si un usuario está logado, si lo está, lo permite */
@Injectable()
export class AuthGuard implements CanActivate {
    constructor(
        private userService: UserService,
        private router: Router,
        private snackBar: MatSnackBar
    ) { }
    /** Comprobación
     *  @returns Si está logado
     */
    canActivate():boolean {
        if (this.userService.getUser()) {
            return true;
        } else {
            this.snackBar.open('Debes iniciar sesión antes', "Cerrar", { duration: 4000, panelClass: 'snackBarWrong' });
            this.router.navigate(['login']);
            return false;
        }
    }
}