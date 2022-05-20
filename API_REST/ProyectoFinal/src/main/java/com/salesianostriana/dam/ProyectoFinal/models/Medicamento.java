package com.salesianostriana.dam.ProyectoFinal.models;

import lombok.*;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
public class Medicamento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;

}
