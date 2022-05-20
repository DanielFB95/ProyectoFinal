package com.salesianostriana.dam.ProyectoFinal.services;

import com.salesianostriana.dam.ProyectoFinal.models.Especialidad;
import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.EspecialidadDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateEspecialidadDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetEspecilidadDto;
import com.salesianostriana.dam.ProyectoFinal.repositories.EspecialidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EspecialidadService {

    private final EspecialidadRepository especialidadRepository;
    private final EspecialidadDtoConverter especialidadDtoConverter;


    public Especialidad save(CreateEspecialidadDto createEspecialidadDto) {

        return especialidadRepository.save(especialidadDtoConverter.createEspecialidadDtoToEspecialidad(createEspecialidadDto));
    }
}
