package com.salesianostriana.dam.ProyectoFinal.models.dto;

import lombok.*;

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
