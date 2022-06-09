package com.salesianostriana.dam.ProyectoFinal.repositories;

import com.salesianostriana.dam.ProyectoFinal.models.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RecetasRepository  extends JpaRepository<Receta,Long>{

    @Query("SELECT r FROM Receta r WHERE paciente_id = :id")
    public List<Receta> recetasDeUnPaciente(@Param("id") UUID id);
}
