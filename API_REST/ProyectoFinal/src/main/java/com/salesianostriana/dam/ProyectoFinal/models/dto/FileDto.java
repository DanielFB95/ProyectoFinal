package com.salesianostriana.dam.ProyectoFinal.models.dto;

import lombok.*;

/**
 * Esta clase define un dto para la obtención y creación de un Fichero
 * @author Daniel Fernández
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {

    private String name;
    private String uri;
    private String type;
    private long size;

}
