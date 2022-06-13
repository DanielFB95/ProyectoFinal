package com.salesianostriana.dam.ProyectoFinal.security.dto;

import com.salesianostriana.dam.ProyectoFinal.users.model.UserRole;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class JwtUserResponse {

    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private String dni;
    private String direccion;
    private String avatar;
    private LocalDate fechaNacimiento;
    private UserRole rol;
    private String token;
}
