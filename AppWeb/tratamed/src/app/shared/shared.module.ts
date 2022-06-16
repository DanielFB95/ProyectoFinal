import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from './material/material.module';
import { MatErrorMessagesDirective } from './directives/matErrorMessages.directive';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ReactiveFormsModule } from '@angular/forms';

//Españolización proyecto
import { OnlyNumberDirective } from './directives/onlyNumber.directive';
import { ElementoTablaComponent } from './tables/elemento-tabla/elemento-tabla.component';
import { TablaComponent } from './tables/tabla/tabla.component';
import { AccionTablaComponent } from './tables/accion-tabla/accion-tabla.component';
import { ConfirmacionComponent } from './confirmacion/confirmacion.component';


@NgModule({
  declarations: [
    MatErrorMessagesDirective,
    OnlyNumberDirective,
    ConfirmacionComponent,
    TablaComponent,
    AccionTablaComponent,
    ElementoTablaComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    FlexLayoutModule,
    ReactiveFormsModule
  ],
  exports: [MaterialModule, AccionTablaComponent, TablaComponent, OnlyNumberDirective, FlexLayoutModule, MatErrorMessagesDirective],
  providers: []
})
export class SharedModule { }
