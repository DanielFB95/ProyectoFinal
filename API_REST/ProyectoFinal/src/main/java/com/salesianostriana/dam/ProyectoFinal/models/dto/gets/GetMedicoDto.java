package com.salesianostriana.dam.ProyectoFinal.models.dto.gets;

import com.salesianostriana.dam.ProyectoFinal.models.Especialidad;
import com.salesianostriana.dam.ProyectoFinal.models.Medico;
import com.salesianostriana.dam.ProyectoFinal.users.model.UserRole;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetMedicoDto {

    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String dni;
    private String direccion;
    private UserRole rol;
    private String numColegiado;
    private Especialidad especialidad;
}
