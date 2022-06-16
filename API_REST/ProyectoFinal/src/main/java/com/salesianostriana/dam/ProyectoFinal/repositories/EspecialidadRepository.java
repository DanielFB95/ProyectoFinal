package com.salesianostriana.dam.ProyectoFinal.repositories;

import com.salesianostriana.dam.ProyectoFinal.models.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {

    boolean existsByNombre(String nombre);
}
