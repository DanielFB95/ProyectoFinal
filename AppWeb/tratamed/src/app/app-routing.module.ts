import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardValoresComponent } from './dashboard-valores/dashboard-valores.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AuthGuard } from './guards/auth.guard';
import { AuthedGuard } from './guards/authed.guard';
import { InicioSesionComponent } from './inicio-sesion/inicio-sesion.component';

const routes: Routes = [
    { path: 'login', component: InicioSesionComponent, canActivate: [AuthedGuard] },
    { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] },
    { path: 'dashboardValores', component: DashboardValoresComponent, canActivate: [AuthGuard] }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
