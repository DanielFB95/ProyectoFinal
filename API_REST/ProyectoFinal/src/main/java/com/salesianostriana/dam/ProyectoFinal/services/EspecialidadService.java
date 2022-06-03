package com.salesianostriana.dam.ProyectoFinal.services;

import com.salesianostriana.dam.ProyectoFinal.models.Especialidad;
import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.EspecialidadDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateEspecialidadDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetEspecialidadDto;
import com.salesianostriana.dam.ProyectoFinal.repositories.EspecialidadRepository;
import com.salesianostriana.dam.ProyectoFinal.users.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EspecialidadService {

    private final EspecialidadRepository especialidadRepository;
    private final EspecialidadDtoConverter especialidadDtoConverter;


    public Especialidad save(CreateEspecialidadDto createEspecialidadDto) {

        return especialidadRepository.save(especialidadDtoConverter.createEspecialidadDtoToEspecialidad(createEspecialidadDto));
    }

    public Especialidad edit(Long id, CreateEspecialidadDto nuevaEspecialidad ){

        return especialidadRepository.findById(id).map(x -> {
                    x.setNombre(nuevaEspecialidad.getNombre());
                    especialidadRepository.save(x);
                    return x;
                }).orElseThrow(() -> new NotFoundException("La especialidad con id: "+ id + "no se ha encontrado."));

    }

    public void delete(Long id){
        especialidadRepository.deleteById(id);
    }

    public GetEspecialidadDto findOne(Long id){
        return especialidadDtoConverter.especialidadToGetEspecialidadDto(
                especialidadRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("La especialidad con id: "+ id + " no se ha encontrado.")));
    }

    public List<GetEspecialidadDto> findAll(){
        return especialidadRepository.findAll().stream()
                .map(especialidadDtoConverter::especialidadToGetEspecialidadDto)
                .collect(Collectors.toList());
    }
}
