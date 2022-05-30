package com.salesianostriana.dam.ProyectoFinal.models.dto.create;

import com.salesianostriana.dam.ProyectoFinal.models.enums.DiasDeTomas;
import com.salesianostriana.dam.ProyectoFinal.models.enums.MomentosDeTomas;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRecetaDto {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<DiasDeTomas> diasDeTomas;
    private List<MomentosDeTomas> momentosDeTomas;

}
