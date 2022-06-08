package com.salesianostriana.dam.ProyectoFinal.models.dto;

import com.salesianostriana.dam.ProyectoFinal.validacion.anotaciones.NameUnique;
import lombok.*;

/**
 * Esta clase define un dto para la obtención y creación de un Medicamento
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicamentoDto {

    @NameUnique(message = "{name.unique}")
    private String nombre;
    private String descripcion;
}
