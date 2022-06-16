package com.salesianostriana.dam.ProyectoFinal.models.dto.gets;

import com.salesianostriana.dam.ProyectoFinal.models.Medicamento;
import com.salesianostriana.dam.ProyectoFinal.models.Medico;
import com.salesianostriana.dam.ProyectoFinal.models.Paciente;
import com.salesianostriana.dam.ProyectoFinal.models.enums.DiasDeTomas;
import com.salesianostriana.dam.ProyectoFinal.models.enums.MomentosDeTomas;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Esta clase define un dto para la obtención de una Receta
 * @author Daniel Fernández
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetRecetaDto {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<DiasDeTomas> diasDeTomas;
    private List<MomentosDeTomas> momentosDeTomas;
    private Medicamento medicamento;
    private GetMedicoDto medico;
    private GetPacienteDto paciente;
}
