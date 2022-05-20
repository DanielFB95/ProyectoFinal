package com.salesianostriana.dam.ProyectoFinal.users.dto;

import com.salesianostriana.dam.ProyectoFinal.users.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityDtoConverter {

    public GetUserEntityDto UserEntityToGetUserEntityDto(UserEntity userEntity){

        return GetUserEntityDto.builder()
                .nombre(userEntity.getNombre())
                .apellidos(userEntity.getApellidos())
                .email(userEntity.getEmail())
                .telefono(userEntity.getTelefono())
                .rol(userEntity.getRol().name())
                .build();
    }
}
