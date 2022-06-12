import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
    selector: 'app-inicio-sesion',
    templateUrl: './inicio-sesion.component.html',
    styleUrls: ['./inicio-sesion.component.scss']
})
export class InicioSesionComponent implements OnInit {
    /** Formulario asociado */
    form: FormGroup;
    constructor(
        private userService: UserService,
        private router: Router
    ) { }

    ngOnInit(): void {
        this.form = new FormGroup({
            email: new FormControl('admin@clinica.es', [Validators.required, Validators.email]),
            password: new FormControl('daniel12', [Validators.required])
        });
    }

    /** Envío del formulario */
    onSubmit() {
        if (this.form.valid) {
            this.userService.login(this.form.get('email').value, this.form.get('password').value).subscribe(logged => {
                if (logged) this.router.navigate(['dashboard']).then(() => {
                    window.location.reload();
                });;
            });
        }
    }

}
