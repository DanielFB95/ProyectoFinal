package com.salesianostriana.dam.ProyectoFinal.models;

import com.salesianostriana.dam.ProyectoFinal.users.model.UserEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@SuperBuilder
public class Paciente extends UserEntity implements Serializable {

    private String observaciones;

    @ManyToOne
    @MapsId("medico_id")
    @JoinColumn(name = "medico_id", foreignKey = @ForeignKey(name = "FK_MEDICO_PACIENTE"))
    private Medico medico;
}
