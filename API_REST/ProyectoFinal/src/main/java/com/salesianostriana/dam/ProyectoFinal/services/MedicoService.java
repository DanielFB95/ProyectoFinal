package com.salesianostriana.dam.ProyectoFinal.services;

import com.salesianostriana.dam.ProyectoFinal.models.Medico;
import com.salesianostriana.dam.ProyectoFinal.models.Paciente;
import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.MedicoDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateMedicoDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetMedicoDto;
import com.salesianostriana.dam.ProyectoFinal.repositories.MedicoRepository;
import com.salesianostriana.dam.ProyectoFinal.repositories.PacienteRepository;
import com.salesianostriana.dam.ProyectoFinal.services.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Esta clase define un servicio para la entidad Medico
 * @author Daniel Fernández
 */
@Service
@RequiredArgsConstructor
public class MedicoService extends BaseService<Medico,UUID,MedicoRepository> {

    private final MedicoRepository medicoRepository;
    private final MedicoDtoConverter medicoDtoConverter;
    private final PacienteRepository pacienteRepository;

    /**
     * Este método edita un médico
     * @param createMedicoDto
     * @param id
     * @return un objeto del tipo GetMedicoDto
     */
    public GetMedicoDto edit (CreateMedicoDto createMedicoDto, UUID id){

        return medicoDtoConverter.medicoToMedicoDto(
                medicoRepository.findById(id).map(x ->{
                        x.setNombre(createMedicoDto.getNombre());
                        x.setApellidos(createMedicoDto.getApellidos());
                        x.setEmail(createMedicoDto.getEmail());
                        x.setDireccion(createMedicoDto.getDireccion());
                        x.setDni(createMedicoDto.getDni());
                        x.setDireccion(createMedicoDto.getDireccion());
                        x.setTelefono(createMedicoDto.getTelefono());
                        x.setNumColegiado(createMedicoDto.getNumColegiado());
                        medicoRepository.save(x);
                        return x;
                }).orElseThrow(()-> new NotFoundException("No se ha encontrado el médico")));
    }

    /**
     * Este método busca y muestra un médico por su id
     * @param id
     * @return un objeto del tipo GetMedicoDto
     */
    public GetMedicoDto findOne(UUID id){
        return medicoDtoConverter.medicoToMedicoDto(medicoRepository.findById(id).orElseThrow(()-> new NotFoundException("No se ha encontrado el médico")));
    }

    /**
     * Este método devuelve una lista de médicos paginada
     * @param pageable
     * @return un objeto del tipo Page<Medico>
     */
    public Page<Medico> findAll(Pageable pageable){
        return medicoRepository.findAll(pageable);
    }

    /**
     * Este método se encarga de borrar un médico por su id
     * @param id
     */
    public void delete(UUID id){

        Medico medico = medicoRepository.findById(id).orElseThrow(()-> new NotFoundException("No se ha encontrado el médico"));
        medico.removeEspecialidadFromMedico();
        medicoRepository.deleteById(id);
    }

    /**
     * Este método muestra una lista de los pacientes de un médico
     * @param id
     * @return un objeto del tipo List<Paciente>
     */
    public List<Paciente> encontrarTodosLosPacientesDeUnMedico(UUID id){
        return pacienteRepository.pacientesDeUnMedico(id);
    }
}
