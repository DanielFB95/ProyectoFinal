import { Directive, ElementRef, HostListener } from '@angular/core';

/** Directiva que hace que no se puedan escribir letras en cualquier input */
@Directive({
  selector: '[numbersOnly]'
})
export class OnlyNumberDirective {

  constructor(private _el: ElementRef) { }
  /** Escucha de evento de input */
  @HostListener('input', ['$event']) onInputChange(event) {
    const initalValue = this._el.nativeElement.value;

    this._el.nativeElement.value = initalValue.replace(/[^0-9]*/g, '');
    if ( initalValue !== this._el.nativeElement.value) {
      event.stopPropagation();
    }
  }
}