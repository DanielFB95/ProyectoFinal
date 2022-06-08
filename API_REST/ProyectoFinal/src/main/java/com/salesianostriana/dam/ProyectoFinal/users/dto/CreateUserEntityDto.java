package com.salesianostriana.dam.ProyectoFinal.users.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Esta clase define un dto para la creación de un UserEntity
 * @author Daniel Fernández
 */
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateUserEntityDto {

    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String dni;
    private String direccion;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate fechaNacimiento;
    private String password;
    private String password2;
}
