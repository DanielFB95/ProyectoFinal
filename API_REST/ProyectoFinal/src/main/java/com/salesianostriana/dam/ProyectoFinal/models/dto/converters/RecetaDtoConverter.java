package com.salesianostriana.dam.ProyectoFinal.models.dto.converters;

import com.salesianostriana.dam.ProyectoFinal.models.Receta;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateRecetaDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetRecetaDto;
import com.salesianostriana.dam.ProyectoFinal.repositories.MedicamentoRepository;
import com.salesianostriana.dam.ProyectoFinal.repositories.MedicoRepository;
import com.salesianostriana.dam.ProyectoFinal.repositories.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Esta clase define un convertidor entre dtos de la entidad Receta
 * @author Daniel Fernández
 */
@Component
@RequiredArgsConstructor
public class RecetaDtoConverter {

    private final MedicoDtoConverter medicoDtoConverter;
    private final PacienteDtoConverter pacienteDtoConverter;

    /**
     *
     * @param createRecetaDto
     * @return un objeto del tipo Receta
     */
    public Receta createRecetaDtoToReceta(CreateRecetaDto createRecetaDto){

        Receta receta = Receta.builder()
                .fechaInicio(createRecetaDto.getFechaInicio())
                .fechaFin(createRecetaDto.getFechaFin())
                .diasDeTomas(createRecetaDto.getDiasDeTomas())
                .momentosDeToma(createRecetaDto.getMomentosDeTomas())
                .build();

        return receta;
    }

    /**
     *
     * @param receta
     * @return un objeto del tipo GetRecetaDto
     */
    public GetRecetaDto recetaToRecetaDto (Receta receta) {
        return GetRecetaDto.builder()
                .fechaInicio(receta.getFechaInicio())
                .fechaFin(receta.getFechaFin())
                .diasDeTomas(receta.getDiasDeTomas())
                .momentosDeTomas(receta.getMomentosDeToma())
                .medicamento(receta.getMedicamento())
                .medico(medicoDtoConverter.medicoToMedicoDto(receta.getMedico()))
                .paciente(pacienteDtoConverter.pacienteToGetPacienteDto(receta.getPaciente()))
                .build();
    }
}
