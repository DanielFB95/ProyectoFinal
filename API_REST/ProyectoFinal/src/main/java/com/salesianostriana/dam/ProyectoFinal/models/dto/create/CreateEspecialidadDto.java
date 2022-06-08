package com.salesianostriana.dam.ProyectoFinal.models.dto.create;

import com.salesianostriana.dam.ProyectoFinal.models.Especialidad;
import com.salesianostriana.dam.ProyectoFinal.validacion.anotaciones.NameUnique;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateEspecialidadDto {

    @NameUnique
    @NotNull
    private String nombre;

}
