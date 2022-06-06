package com.salesianostriana.dam.ProyectoFinal.services;

import com.salesianostriana.dam.ProyectoFinal.models.Medico;
import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.MedicoDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateMedicoDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetMedicoDto;
import com.salesianostriana.dam.ProyectoFinal.repositories.MedicoRepository;
import com.salesianostriana.dam.ProyectoFinal.services.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicoService extends BaseService<Medico,UUID,MedicoRepository> {

    private final MedicoRepository medicoRepository;
    private final MedicoDtoConverter medicoDtoConverter;

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

    public GetMedicoDto findOne(UUID id){
        return medicoDtoConverter.medicoToMedicoDto(medicoRepository.findById(id).orElseThrow(()-> new NotFoundException("No se ha encontrado el médico")));
    }

    public Page<Medico> findAll(Pageable pageable){
        return medicoRepository.findAll(pageable);
    }

    public void delete(UUID id){

        Medico medico = medicoRepository.findById(id).orElseThrow(()-> new NotFoundException("No se ha encontrado el médico"));
        medico.removeEspecialidadFromMedico();
        medicoRepository.deleteById(id);
    }
}
