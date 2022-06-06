package com.salesianostriana.dam.ProyectoFinal.services;

import com.salesianostriana.dam.ProyectoFinal.models.Paciente;
import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.PacienteDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreatePacienteDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetPacienteDto;
import com.salesianostriana.dam.ProyectoFinal.repositories.PacienteRepository;
import com.salesianostriana.dam.ProyectoFinal.services.base.BaseService;
import com.salesianostriana.dam.ProyectoFinal.users.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteService extends BaseService<Paciente,UUID,PacienteRepository> {

    private final PacienteDtoConverter pacienteDtoConverter;
    private final PacienteRepository pacienteRepository;

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

    public GetPacienteDto findOne(UUID id){
        return pacienteDtoConverter.pacienteToGetPacienteDto(pacienteRepository.findById(id).orElseThrow(()-> new NotFoundException("Paciente no encontrado")));
    }

    public Page<Paciente> findAll(Pageable pageable){
        return pacienteRepository.findAll(pageable);
    }

    public void delete(UUID id){
        pacienteRepository.deleteById(id);
    }
}
