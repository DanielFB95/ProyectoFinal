package com.salesianostriana.dam.ProyectoFinal.validacion.anotaciones;

import com.salesianostriana.dam.ProyectoFinal.validacion.validadores.NameUniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = NameUniqueValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
@Documented
public @interface NameUnique {

    String message() default "El nombre debe ser único";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
