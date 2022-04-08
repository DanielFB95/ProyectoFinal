package com.salesianostriana.dam.ProyectoFinal.users.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateUserEntityDto {

    private String nombre;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String avatar;
    private String email;
    private String password;
    private String password2;
}
