package com.salesianostriana.dam.ProyectoFinal.models.dto.converters;

import com.salesianostriana.dam.ProyectoFinal.models.Especialidad;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateEspecialidadDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetEspecialidadDto;
import org.springframework.stereotype.Component;
/**
 * Esta clase define un convertidor entre dtos de la entidad Especilidad
 * @author Daniel Fernández
 */
@Component
public class EspecialidadDtoConverter {

    /**
     * @param especialidadDto
     * @return un objeto del tipo Especilidad
     */
    public Especialidad createEspecialidadDtoToEspecialidad (CreateEspecialidadDto especialidadDto){

        Especialidad especialidad = Especialidad.builder()
                .nombre(especialidadDto.getNombre())
                .build();
        return especialidad;
    }

    /**
     *
     * @param especialidad
     * @return un objeto del tipo GetEspecilidadDto
     */
    public GetEspecialidadDto especialidadToGetEspecialidadDto (Especialidad especialidad){

        GetEspecialidadDto getEspecilidadDto = GetEspecialidadDto.builder()
                .nombre(especialidad.getNombre())
                .build();

        return getEspecilidadDto;
    }
}
