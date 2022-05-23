package com.salesianostriana.dam.ProyectoFinal.users.controller;

import com.salesianostriana.dam.ProyectoFinal.users.dto.CreateUserEntityDto;
import com.salesianostriana.dam.ProyectoFinal.users.dto.GetUserEntityDto;
import com.salesianostriana.dam.ProyectoFinal.users.dto.UserEntityDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.users.service.UserEntityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "Usuario", description = "Controller usuario")
public class UserEntityController {

    private final UserEntityService userEntityService;
    private final UserEntityDtoConverter userEntityDtoConverter;

    @PostMapping("/admin")
    public GetUserEntityDto saveAdmin(@RequestBody CreateUserEntityDto createUserEntityDto){
        return userEntityDtoConverter.UserEntityToGetUserEntityDto(userEntityService.saveAdmin(createUserEntityDto));
    }


}
