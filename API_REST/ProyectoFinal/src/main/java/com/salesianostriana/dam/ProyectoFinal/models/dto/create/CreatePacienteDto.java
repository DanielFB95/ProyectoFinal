package com.salesianostriana.dam.ProyectoFinal.models.dto.create;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salesianostriana.dam.ProyectoFinal.models.Especialidad;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Esta clase define un dto para la creación de Pacientes
 * @author Daniel Fernández
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreatePacienteDto {

    @NotNull
    private String nombre;
    @NotNull
    private String apellidos;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate fechaNacimiento;
    @NotNull
    private String email;
    private String telefono;
    @NotNull
    private String dni;
    private String direccion;
    private String password;
    private String password2;
    private String observaciones;
}
