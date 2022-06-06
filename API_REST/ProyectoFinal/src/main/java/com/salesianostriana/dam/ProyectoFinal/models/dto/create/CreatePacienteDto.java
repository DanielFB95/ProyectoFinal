package com.salesianostriana.dam.ProyectoFinal.models.dto.create;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salesianostriana.dam.ProyectoFinal.models.Especialidad;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreatePacienteDto {

    private String nombre;
    private String apellidos;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate fechaNacimiento;
    private String email;
    private String telefono;
    private String dni;
    private String direccion;
    private String password;
    private String password2;
    private String observaciones;
}
