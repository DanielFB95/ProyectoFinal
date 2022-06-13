import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SharedModule } from './shared/shared.module';
import { InicioSesionComponent } from './inicio-sesion/inicio-sesion.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SharedService } from './shared/shared.service';
import { FormularioMedicoComponent } from './formularios/formulario-medico/formulario-medico.component';
import { UserService } from './services/user.service';
import { RestService } from './services/rest.service';
import { HttpClientModule } from '@angular/common/http';
import { MedicoService } from './services/medico.service';
import { DashboardValoresComponent } from './dashboard-valores/dashboard-valores.component';
import { FormularioMedicamentoComponent } from './formularios/formulario-medicamento/formulario-medicamento.component';
import { FormularioEspecialidadComponent } from './formularios/formulario-especialidad/formulario-especialidad.component';
import { MedicamentoService } from './services/medicamento.service';
import { EspecialidadService } from './services/especialidad.service';
import { AuthGuard } from './guards/auth.guard';
import { AuthedGuard } from './guards/authed.guard';

@NgModule({
  declarations: [
    AppComponent,
    InicioSesionComponent,
    DashboardComponent,
    FormularioMedicoComponent,
    DashboardValoresComponent,
    FormularioMedicamentoComponent,
    FormularioEspecialidadComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    HttpClientModule
  ],
  providers: [SharedService,RestService,  UserService, MedicoService, MedicamentoService, EspecialidadService, AuthGuard, AuthedGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
