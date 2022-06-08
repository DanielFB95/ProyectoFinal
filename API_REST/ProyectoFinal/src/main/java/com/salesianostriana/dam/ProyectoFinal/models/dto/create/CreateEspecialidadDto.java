package com.salesianostriana.dam.ProyectoFinal.models.dto.create;

import com.salesianostriana.dam.ProyectoFinal.models.Especialidad;
import com.salesianostriana.dam.ProyectoFinal.validacion.anotaciones.NameUnique;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * Esta clase define un dto para la creación de Epecialidades
 * @author Daniel Fernández
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateEspecialidadDto {

    @NameUnique(message = "{name.unique}")
    @NotNull
    private String nombre;

}
