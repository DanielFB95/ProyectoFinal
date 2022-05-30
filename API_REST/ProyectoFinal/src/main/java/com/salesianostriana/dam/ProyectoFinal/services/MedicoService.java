package com.salesianostriana.dam.ProyectoFinal.services;

import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.MedicoDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateMedicoDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetMedicoDto;
import com.salesianostriana.dam.ProyectoFinal.repositories.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicoService {

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
                        medicoRepository.save(x);
                        return x;
                }).orElseThrow(()-> new NotFoundException("No se ha encontrado el médico")));
    }

    public GetMedicoDto findOne(UUID id){
        return medicoDtoConverter.medicoToMedicoDto(medicoRepository.findById(id).orElseThrow(()-> new NotFoundException("No se ha encontrado el médico")));
    }

    public List<GetMedicoDto> findAll(){
        return medicoRepository.findAll().stream()
                .map(medicoDtoConverter::medicoToMedicoDto)
                .collect(Collectors.toList());
    }

    public void delete(UUID id){
        medicoRepository.deleteById(id);
    }
}
