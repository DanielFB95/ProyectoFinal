package com.salesianostriana.dam.ProyectoFinal.models.dto.converters;

import com.salesianostriana.dam.ProyectoFinal.models.Medico;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateMedicoDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetMedicoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MedicoDtoConverter {

    private final EspecialidadDtoConverter especialidadDtoConverter;

    public Medico createMedicoDtoToMedico (CreateMedicoDto createMedicoDto){
        Medico medico = Medico.builder()
                .nombre(createMedicoDto.getNombre())
                .apellidos(createMedicoDto.getApellidos())
                .email(createMedicoDto.getEmail())
                .telefono(createMedicoDto.getTelefono())
                .dni(createMedicoDto.getDni())
                .direccion(createMedicoDto.getDireccion())
                .numColegiado(createMedicoDto.getNumColegiado())
                .password(createMedicoDto.getPassword())
                .build();
        return medico;
    }

    public GetMedicoDto medicoToMedicoDto (Medico medico){

        GetMedicoDto getMedicoDto = GetMedicoDto.builder()
                .id(medico.getId())
                .nombre(medico.getNombre())
                .apellidos(medico.getApellidos())
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
