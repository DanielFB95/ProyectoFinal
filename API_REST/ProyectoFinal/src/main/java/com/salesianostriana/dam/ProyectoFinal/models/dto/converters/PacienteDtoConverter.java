package com.salesianostriana.dam.ProyectoFinal.models.dto.converters;

import com.salesianostriana.dam.ProyectoFinal.models.Paciente;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreatePacienteDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetPacienteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PacienteDtoConverter {

    private final MedicoDtoConverter medicoDtoConverter;

    public Paciente createPacienteDtoToPaciente (CreatePacienteDto createPacienteDto){

        Paciente paciente = Paciente.builder()
                .nombre(createPacienteDto.getNombre())
                .apellidos(createPacienteDto.getApellidos())
                .fechaNacimiento(createPacienteDto.getFechaNacimiento())
                .email(createPacienteDto.getEmail())
                .telefono(createPacienteDto.getTelefono())
                .dni(createPacienteDto.getDni())
                .direccion(createPacienteDto.getDireccion())
                .observaciones(createPacienteDto.getObservaciones())
                .password(createPacienteDto.getPassword())
                .build();

        return paciente;
    }

    public GetPacienteDto pacienteToGetPacienteDto(Paciente paciente){

        GetPacienteDto getPacienteDto = GetPacienteDto.builder()
                .id(paciente.getId())
                .nombre(paciente.getNombre())
                .apellidos(paciente.getApellidos())
                .fechaNacimiento(paciente.getFechaNacimiento())
                .avatar(paciente.getAvatar())
                .email(paciente.getEmail())
                .telefono(paciente.getTelefono())
                .dni(paciente.getDni())
                .direccion(paciente.getDireccion())
                .rol(paciente.getRol())
                .observaciones(paciente.getObservaciones())
                .medico(medicoDtoConverter.medicoToMedicoDto(paciente.getMedico()))
                .build();

        return getPacienteDto;
    }
}
