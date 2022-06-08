package com.salesianostriana.dam.ProyectoFinal.models;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Esta clase define la entidad Especiaidad
 * @author Daniel Fernández
 */

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
public class Especialidad implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToOne(mappedBy = "especialidad", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Medico medico;

    public Especialidad (String nombre){
        this.nombre = nombre;
    }
}
