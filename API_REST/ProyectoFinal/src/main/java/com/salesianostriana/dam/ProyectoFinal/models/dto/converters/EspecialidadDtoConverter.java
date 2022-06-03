package com.salesianostriana.dam.ProyectoFinal.models.dto.converters;

import com.salesianostriana.dam.ProyectoFinal.models.Especialidad;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateEspecialidadDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetEspecialidadDto;
import org.springframework.stereotype.Component;

@Component
public class EspecialidadDtoConverter {

    public Especialidad createEspecialidadDtoToEspecialidad (CreateEspecialidadDto especialidadDto){

        Especialidad especialidad = Especialidad.builder()
                .nombre(especialidadDto.getNombre())
                .build();
        return especialidad;
    }

    public GetEspecialidadDto especialidadToGetEspecialidadDto (Especialidad especialidad){

        GetEspecialidadDto getEspecilidadDto = GetEspecialidadDto.builder()
                .nombre(especialidad.getNombre())
                .build();

        return getEspecilidadDto;
    }
}
