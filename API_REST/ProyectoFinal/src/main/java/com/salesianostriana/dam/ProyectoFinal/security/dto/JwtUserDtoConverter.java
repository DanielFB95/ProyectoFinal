package com.salesianostriana.dam.ProyectoFinal.security.dto;

import com.salesianostriana.dam.ProyectoFinal.users.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class JwtUserDtoConverter {

    public JwtUserResponse userToJwtUserResponse(UserEntity userEntity, String jwt){
        return JwtUserResponse.builder()
                .nombre(userEntity.getNombre())
                .apellidos(userEntity.getApellidos())
                .telefono(userEntity.getTelefono())
                .email(userEntity.getEmail())
                .token(jwt)
                .build();
    }
}