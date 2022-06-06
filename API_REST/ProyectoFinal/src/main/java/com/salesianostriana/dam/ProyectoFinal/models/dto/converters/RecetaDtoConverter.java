package com.salesianostriana.dam.ProyectoFinal.models.dto.converters;

import com.salesianostriana.dam.ProyectoFinal.models.Receta;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateRecetaDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetRecetaDto;
import com.salesianostriana.dam.ProyectoFinal.repositories.MedicamentoRepository;
import com.salesianostriana.dam.ProyectoFinal.repositories.MedicoRepository;
import com.salesianostriana.dam.ProyectoFinal.repositories.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecetaDtoConverter {

    private final MedicoDtoConverter medicoDtoConverter;
    private final PacienteDtoConverter pacienteDtoConverter;

    public Receta createRecetaDtoToReceta(CreateRecetaDto createRecetaDto){

        Receta receta = Receta.builder()
                .fechaInicio(createRecetaDto.getFechaInicio())
                .fechaFin(createRecetaDto.getFechaFin())
                .diasDeTomas(createRecetaDto.getDiasDeTomas())
                .momentosDeToma(createRecetaDto.getMomentosDeTomas())
                .build();

        return receta;
    }

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
