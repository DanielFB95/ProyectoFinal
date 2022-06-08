package com.salesianostriana.dam.ProyectoFinal.models.dto;

import com.salesianostriana.dam.ProyectoFinal.validacion.anotaciones.NameUnique;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicamentoDto {

    @NameUnique
    private String nombre;
    private String descripcion;
}
