package com.salesianostriana.dam.ProyectoFinal.users.dto;

import com.salesianostriana.dam.ProyectoFinal.users.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityDtoConverter {

    public GetUserEntityDto UserEntityToGetUserEntityDto(UserEntity userEntity){

        return GetUserEntityDto.builder()
                .id(userEntity.getId())
                .nombre(userEntity.getNombre())
                .apellidos(userEntity.getApellidos())
                .email(userEntity.getEmail())
                .direccion(userEntity.getDireccion())
                .telefono(userEntity.getTelefono())
                .rol(userEntity.getRol().name())
                .build();
    }
}
