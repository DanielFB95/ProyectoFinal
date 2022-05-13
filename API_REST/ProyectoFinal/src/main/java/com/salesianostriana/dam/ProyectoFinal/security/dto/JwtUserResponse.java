package com.salesianostriana.dam.ProyectoFinal.security.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class JwtUserResponse {

    private String nombre;
    private String apellidos;
    private String telefono;
    private String direccion;
    private String email;
    private String token;
}
