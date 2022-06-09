package com.salesianostriana.dam.ProyectoFinal.services;

import com.salesianostriana.dam.ProyectoFinal.models.Especialidad;
import com.salesianostriana.dam.ProyectoFinal.models.Medico;
import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.EspecialidadDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateEspecialidadDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetEspecialidadDto;
import com.salesianostriana.dam.ProyectoFinal.repositories.EspecialidadRepository;
import com.salesianostriana.dam.ProyectoFinal.repositories.MedicoRepository;
import com.salesianostriana.dam.ProyectoFinal.services.base.BaseService;
import com.salesianostriana.dam.ProyectoFinal.users.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;
/**
 * Esta clase define un servicio para la entidad Especilidad
 * @author Daniel Fernández
 */
@Service
@RequiredArgsConstructor
public class EspecialidadService extends BaseService<Especialidad,Long,EspecialidadRepository> {

    private final EspecialidadRepository especialidadRepository;
    private final EspecialidadDtoConverter especialidadDtoConverter;
    private final MedicoRepository medicoRepository;

    /**
     * Este método genera una nueva especilidad
     * @param createEspecialidadDto
     * @return un objeto del tipo Especilidad
     */
    public Especialidad save(CreateEspecialidadDto createEspecialidadDto) {

        return especialidadRepository.save(especialidadDtoConverter.createEspecialidadDtoToEspecialidad(createEspecialidadDto));
    }

    /**
     * Este método edita una especialidad
     * @param id
     * @param nuevaEspecialidad
     * @return un objeto del tipo Especialidad
     */
    public Especialidad edit(Long id, CreateEspecialidadDto nuevaEspecialidad ){

        return especialidadRepository.findById(id).map(x -> {
                    x.setNombre(nuevaEspecialidad.getNombre());
                    especialidadRepository.save(x);
                    return x;
                }).orElseThrow(() -> new NotFoundException("La especialidad con id: "+ id + "no se ha encontrado."));

    }

    /**
     * Este método borra una especialidad por su id
     * @param id
     */
    public void delete(Long id){
        medicoRepository.buscarMedicosPorEspecialidad(id).stream().map(x ->{
            x.removeEspecialidadFromMedico();
            return x;
        }).collect(Collectors.toList());
        especialidadRepository.deleteById(id);
    }

    /**
     * Este método busca y muestra una especialidad por su id
     * @param id
     * @return un objeto del tipo GetEspecialidadDto
     */
    public GetEspecialidadDto findOne(Long id){
        return especialidadDtoConverter.especialidadToGetEspecialidadDto(
                especialidadRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("La especialidad con id: "+ id + " no se ha encontrado.")));
    }

    /**
     * Este método devuelve una lista con todas las especialidades paginadas
     * @param pageable
     * @return un objeto de tipo Page<Especialidad>
     */
    public Page<Especialidad> findAll(Pageable pageable){
        return especialidadRepository.findAll(pageable);}
}
