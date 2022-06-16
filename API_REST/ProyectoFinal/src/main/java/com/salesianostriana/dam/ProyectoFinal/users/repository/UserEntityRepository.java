package com.salesianostriana.dam.ProyectoFinal.users.repository;

import com.salesianostriana.dam.ProyectoFinal.users.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findFirstByEmail(String email);
}
