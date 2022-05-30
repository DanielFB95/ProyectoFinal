package com.salesianostriana.dam.ProyectoFinal.repositories;

import com.salesianostriana.dam.ProyectoFinal.models.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MedicoRepository extends JpaRepository<Medico, UUID> {
}
