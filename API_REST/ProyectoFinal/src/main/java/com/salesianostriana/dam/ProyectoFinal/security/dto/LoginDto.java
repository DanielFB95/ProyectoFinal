package com.salesianostriana.dam.ProyectoFinal.security.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {

    private String email;
    private String password;
}
