package com.salesianostriana.dam.ProyectoFinal.models.dto.create;

import com.salesianostriana.dam.ProyectoFinal.models.Especialidad;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateMedicoDto {

    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String dni;
    private String direccion;
    private String password;
    private String password2;
    private String numColegiado;
    private Long especialidad;
}
