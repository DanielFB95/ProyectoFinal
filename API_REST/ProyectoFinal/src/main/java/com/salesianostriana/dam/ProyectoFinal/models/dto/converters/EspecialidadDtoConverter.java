package com.salesianostriana.dam.ProyectoFinal.models.dto.converters;

import com.salesianostriana.dam.ProyectoFinal.models.Especialidad;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateEspecialidadDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetEspecilidadDto;
import org.springframework.stereotype.Component;

@Component
public class EspecialidadDtoConverter {

    public Especialidad createEspecialidadDtoToEspecialidad (CreateEspecialidadDto especialidadDto){

        Especialidad especialidad = Especialidad.builder()
                .nombre(especialidadDto.getNombre())
                .build();
        return especialidad;
    }

    public GetEspecilidadDto especialidadToGetEspecialidadDto (Especialidad especialidad){

        GetEspecilidadDto getEspecilidadDto = GetEspecilidadDto.builder()
                .nombre(especialidad.getNombre())
                .build();

        return getEspecilidadDto;
    }
}
