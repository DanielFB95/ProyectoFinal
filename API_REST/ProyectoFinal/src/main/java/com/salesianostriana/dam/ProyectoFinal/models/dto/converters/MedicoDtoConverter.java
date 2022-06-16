package com.salesianostriana.dam.ProyectoFinal.models.dto.converters;

import com.salesianostriana.dam.ProyectoFinal.models.Especialidad;
import com.salesianostriana.dam.ProyectoFinal.models.Medico;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateMedicoDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetMedicoDto;
import com.salesianostriana.dam.ProyectoFinal.repositories.EspecialidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

/**
 * Esta clase define un convertidor entre dtos de la entidad Medico
 * @author Daniel Fernández
 */
@Component
@RequiredArgsConstructor
public class MedicoDtoConverter {

    private final EspecialidadDtoConverter especialidadDtoConverter;
    private final EspecialidadRepository especialidadRepository;

    /**
     *
     * @param createMedicoDto
     * @return un objeto del tipo Medico
     */
    public Medico createMedicoDtoToMedico (CreateMedicoDto createMedicoDto){
        Medico medico = Medico.builder()
                .nombre(createMedicoDto.getNombre())
                .apellidos(createMedicoDto.getApellidos())
                .fechaNacimiento(createMedicoDto.getFechaNacimiento())
                .email(createMedicoDto.getEmail())
                .telefono(createMedicoDto.getTelefono())
                .dni(createMedicoDto.getDni())
                .direccion(createMedicoDto.getDireccion())
                .numColegiado(createMedicoDto.getNumColegiado())
                .password(createMedicoDto.getPassword())
                .build();
        return medico;
    }

    /**
     *
     * @param medico
     * @return un objeto del tipo GetMedicoDto
     */
    public GetMedicoDto medicoToMedicoDto (Medico medico){

        if(medico.getEspecialidad() == null){
            GetMedicoDto getMedicoDto = GetMedicoDto.builder()
                    .id(medico.getId())
                    .nombre(medico.getNombre())
                    .apellidos(medico.getApellidos())
                    .fechaNacimiento(medico.getFechaNacimiento())
                    .avatar(medico.getAvatar())
                    .email(medico.getEmail())
                    .telefono(medico.getTelefono())
                    .dni(medico.getDni())
                    .direccion(medico.getDireccion())
                    .rol(medico.getRol())
                    .numColegiado(medico.getNumColegiado())
                    .build();

            return getMedicoDto;
        }else{
            GetMedicoDto getMedicoDto = GetMedicoDto.builder()
                    .id(medico.getId())
                    .nombre(medico.getNombre())
                    .apellidos(medico.getApellidos())
                    .fechaNacimiento(medico.getFechaNacimiento())
                    .avatar(medico.getAvatar())
                    .email(medico.getEmail())
                    .telefono(medico.getTelefono())
                    .dni(medico.getDni())
                    .direccion(medico.getDireccion())
                    .rol(medico.getRol())
                    .numColegiado(medico.getNumColegiado())
                    .especialidad(especialidadDtoConverter.especialidadToGetEspecialidadDto(medico.getEspecialidad()))
                    .build();

            return getMedicoDto;
        }
    }
}
