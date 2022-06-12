import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from './material/material.module';
import { MatErrorMessagesDirective } from './directives/matErrorMessages.directive';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ReactiveFormsModule } from '@angular/forms';

//Españolización proyecto
import { OnlyNumberDirective } from './directives/onlyNumber.directive';
import { ConfirmacionComponent } from './confirmacion/confirmacion.component';


@NgModule({
  declarations: [
    MatErrorMessagesDirective,
    OnlyNumberDirective,
    ConfirmacionComponent,
  ],
  imports: [
    CommonModule,
    MaterialModule,
    FlexLayoutModule,
    ReactiveFormsModule
  ],
  exports: [MaterialModule,OnlyNumberDirective, FlexLayoutModule, MatErrorMessagesDirective],
  providers: []
})
export class SharedModule { }
