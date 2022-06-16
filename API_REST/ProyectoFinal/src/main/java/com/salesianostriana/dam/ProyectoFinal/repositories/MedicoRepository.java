package com.salesianostriana.dam.ProyectoFinal.repositories;

import com.salesianostriana.dam.ProyectoFinal.models.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MedicoRepository extends JpaRepository<Medico, UUID> {

    @Query("SELECT m FROM Medico m WHERE especialidad_id = :id")
    public List<Medico> buscarMedicosPorEspecialidad(@Param("id") Long id);
}
