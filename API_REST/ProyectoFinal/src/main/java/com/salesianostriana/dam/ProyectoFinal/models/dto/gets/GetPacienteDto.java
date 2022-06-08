package com.salesianostriana.dam.ProyectoFinal.models.dto.gets;

import com.salesianostriana.dam.ProyectoFinal.models.Medico;

import com.salesianostriana.dam.ProyectoFinal.users.model.UserRole;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Esta clase define un dto para la obtención de un Paciente
 * @author Daniel Fernández
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetPacienteDto {

    private UUID id;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String avatar;
    private String email;
    private String telefono;
    private String dni;
    private String direccion;
    private UserRole rol;
    private String observaciones;
    private GetMedicoDto medico;
}
