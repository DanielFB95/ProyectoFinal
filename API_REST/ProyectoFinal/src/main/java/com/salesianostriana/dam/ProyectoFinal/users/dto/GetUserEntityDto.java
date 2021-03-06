package com.salesianostriana.dam.ProyectoFinal.users.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


/**
 * Esta clase define un dto para la obtención de un UserEntity
 * @author Daniel Fernández
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetUserEntityDto {

    private UUID id;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String direccion;
    private String telefono;
    private String avatar;
    private String email;
    private String rol;

}
