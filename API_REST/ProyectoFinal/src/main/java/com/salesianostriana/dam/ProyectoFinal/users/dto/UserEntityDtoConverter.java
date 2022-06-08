package com.salesianostriana.dam.ProyectoFinal.users.dto;

import com.salesianostriana.dam.ProyectoFinal.users.model.UserEntity;
import org.springframework.stereotype.Component;

/**
 * Esta clase define un convertidor entre dtos de la entidad UserEntity
 * @author Daniel Fernández
 */
@Component
public class UserEntityDtoConverter {

    /**
     *
     * @param userEntity
     * @return un objeto del tipo GetUserEntityDto
     */
    public GetUserEntityDto UserEntityToGetUserEntityDto(UserEntity userEntity){

        return GetUserEntityDto.builder()
                .id(userEntity.getId())
                .nombre(userEntity.getNombre())
                .apellidos(userEntity.getApellidos())
                .fechaNacimiento(userEntity.getFechaNacimiento())
                .email(userEntity.getEmail())
                .direccion(userEntity.getDireccion())
                .telefono(userEntity.getTelefono())
                .rol(userEntity.getRol().name())
                .build();
    }
}
