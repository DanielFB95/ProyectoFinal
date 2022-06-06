package com.salesianostriana.dam.ProyectoFinal.models.dto.create;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salesianostriana.dam.ProyectoFinal.models.enums.DiasDeTomas;
import com.salesianostriana.dam.ProyectoFinal.models.enums.MomentosDeTomas;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRecetaDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate fechaInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate fechaFin;

    private List<DiasDeTomas> diasDeTomas;
    private List<MomentosDeTomas> momentosDeTomas;
    private Long idMedicamento;
    private UUID idPaciente;

}
