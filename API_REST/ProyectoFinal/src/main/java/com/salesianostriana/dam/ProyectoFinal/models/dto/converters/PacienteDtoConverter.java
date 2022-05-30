package com.salesianostriana.dam.ProyectoFinal.models.dto.converters;

import com.salesianostriana.dam.ProyectoFinal.models.Paciente;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreatePacienteDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetPacienteDto;
import org.springframework.stereotype.Component;

@Component
public class PacienteDtoConverter {

    public Paciente createPacienteDtoToPaciente (CreatePacienteDto createPacienteDto){

        Paciente paciente = Paciente.builder()
                .nombre(createPacienteDto.getNombre())
                .apellidos(createPacienteDto.getApellidos())
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
                .nombre(paciente.getNombre())
                .apellidos(paciente.getApellidos())
                .email(paciente.getEmail())
                .telefono(paciente.getTelefono())
                .dni(paciente.getDni())
                .direccion(paciente.getDireccion())
                .rol(paciente.getRol())
                .observaciones(paciente.getObservaciones())
                .build();

        return getPacienteDto;
    }
}
