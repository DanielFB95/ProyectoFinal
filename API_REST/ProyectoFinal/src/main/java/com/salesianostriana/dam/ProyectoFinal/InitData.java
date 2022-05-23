package com.salesianostriana.dam.ProyectoFinal;

import com.salesianostriana.dam.ProyectoFinal.users.model.UserEntity;
import com.salesianostriana.dam.ProyectoFinal.users.model.UserRole;
import com.salesianostriana.dam.ProyectoFinal.users.repository.UserEntityRepository;
import com.salesianostriana.dam.ProyectoFinal.users.service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitData {

    private final UserEntityService userEntityService;
    private final PasswordEncoder passwordEncoder;

/*    @PostConstruct
    public void init(){

        UserEntity admin = UserEntity.builder()
                .nombre("Daniel")
                .apellidos("Fernández Bernal")
                .email("fernandez.bedan20@triana.salesianos.edu")
                .telefono("555-555-555")
                .dni("A123456789")
                .password("daniel12")
                //.password(passwordEncoder.encode("daniel12"))
                .direccion("Calle Gran Poder, 1, Benacazón")
                .rol(UserRole.ADMIN)
                .build();

        userEntityService.save(admin);
    }*/
}
