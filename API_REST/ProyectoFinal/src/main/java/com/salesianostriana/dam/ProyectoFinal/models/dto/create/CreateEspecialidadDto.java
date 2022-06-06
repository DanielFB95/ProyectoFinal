package com.salesianostriana.dam.ProyectoFinal.models.dto.create;

import com.salesianostriana.dam.ProyectoFinal.models.Especialidad;
import com.salesianostriana.dam.ProyectoFinal.validacion.anotaciones.NameUnique;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateEspecialidadDto {

    @NameUnique
    private String nombre;

}
