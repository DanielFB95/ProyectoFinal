import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from './model/Usuario';
import { UserService } from './services/user.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent {
    title = 'clinic';
    usuarioLogado: Usuario;

    constructor(
        private usuarioService: UserService,
        private router:Router
    ) {

    }
    ngOnInit() {
        this.usuarioLogado = this.usuarioService.getUser();
        if(!this.usuarioLogado){
            this.router.navigate(['login']);
        }
        this.usuarioService.usuarioSeteado.subscribe(usuario => {
            this.usuarioLogado = usuario;
        })
    }

    logout() {
        this.usuarioLogado = null;
        this.usuarioService.logout();
    }
}
