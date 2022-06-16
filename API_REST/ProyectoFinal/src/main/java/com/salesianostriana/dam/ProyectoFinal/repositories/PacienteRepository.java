package com.salesianostriana.dam.ProyectoFinal.repositories;

import com.salesianostriana.dam.ProyectoFinal.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PacienteRepository extends JpaRepository<Paciente, UUID> {

    @Query("SELECT p FROM Paciente p WHERE medico_id = :id")
    public List<Paciente> pacientesDeUnMedico(@Param("id") UUID id);
}
