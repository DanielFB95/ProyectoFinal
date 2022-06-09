package com.salesianostriana.dam.ProyectoFinal.services;

import com.salesianostriana.dam.ProyectoFinal.models.Paciente;
import com.salesianostriana.dam.ProyectoFinal.models.Receta;
import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.PacienteDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreatePacienteDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetPacienteDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetRecetaDto;
import com.salesianostriana.dam.ProyectoFinal.repositories.PacienteRepository;
import com.salesianostriana.dam.ProyectoFinal.repositories.RecetasRepository;
import com.salesianostriana.dam.ProyectoFinal.services.base.BaseService;
import com.salesianostriana.dam.ProyectoFinal.users.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
/**
 * Esta clase define un servicio para la entidad Paciente
 * @author Daniel Fernández
 */
@Service
@RequiredArgsConstructor
public class PacienteService extends BaseService<Paciente,UUID,PacienteRepository> {

    private final PacienteDtoConverter pacienteDtoConverter;
    private final PacienteRepository pacienteRepository;
    private final RecetasRepository recetasRepository;

    /**
     * Este método edita un paciente
     * @param createPacienteDto
     * @param id
     * @return un objeto del tipo GetPacienteDto
     */
    public GetPacienteDto edit (CreatePacienteDto createPacienteDto, UUID id){

        return pacienteDtoConverter.pacienteToGetPacienteDto(pacienteRepository.findById(id).map(x -> {
            x.setNombre(createPacienteDto.getNombre());
            x.setApellidos(createPacienteDto.getApellidos());
            x.setEmail(createPacienteDto.getEmail());
            x.setDireccion(createPacienteDto.getDireccion());
            x.setDni(createPacienteDto.getDni());
            x.setDireccion(createPacienteDto.getDireccion());
            x.setTelefono(createPacienteDto.getTelefono());
            pacienteRepository.save(x);
            return x;
        }).orElseThrow(()-> new NotFoundException("No se ha encontrado al paciente")));

    }

    /**
     * Este método busca y muestra un paciente por su id
     * @param id
     * @return un objeto del tipo GetPacienteDto
     */
    public GetPacienteDto findOne(UUID id){
        return pacienteDtoConverter.pacienteToGetPacienteDto(pacienteRepository.findById(id).orElseThrow(()-> new NotFoundException("Paciente no encontrado")));
    }

    /**
     * Este método devuelve una lista de pacientes paginada
     * @param pageable
     * @return un objeto del tipo Page<Paciente>
     */
    public Page<Paciente> findAll(Pageable pageable){
        return pacienteRepository.findAll(pageable);
    }

    /**
     * Este método borra un paciente por su id
     * @param id
     */
    public void delete(UUID id){
        pacienteRepository.deleteById(id);
    }

    /**
     * Este método muestra una lista de las recetas de un paciente
     * @param id
     * @return un objeto del tipo List<Receta>
     */
    public List<Receta> encontrarLasRecetasDeUnPaciente(UUID id){
        return recetasRepository.recetasDeUnPaciente(id);
    }
}
