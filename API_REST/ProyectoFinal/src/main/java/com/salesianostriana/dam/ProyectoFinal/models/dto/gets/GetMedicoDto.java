package com.salesianostriana.dam.ProyectoFinal.models.dto.gets;

import com.salesianostriana.dam.ProyectoFinal.models.Especialidad;
import com.salesianostriana.dam.ProyectoFinal.models.Medico;
import com.salesianostriana.dam.ProyectoFinal.users.model.UserRole;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetMedicoDto {

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
    private String numColegiado;
    private GetEspecialidadDto especialidad;
}
