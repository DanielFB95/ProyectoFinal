package com.salesianostriana.dam.ProyectoFinal.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Esta clase define la entidad Medicamento
 * @author Daniel Fernández
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
public class Medicamento implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;

}
