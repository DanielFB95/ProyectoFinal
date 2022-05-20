package com.salesianostriana.dam.ProyectoFinal.models;

import com.salesianostriana.dam.ProyectoFinal.users.model.UserEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@SuperBuilder
public class Medico extends UserEntity {

    @Column(name = "numcolegiado")
    private String numColegiado;

    @ManyToOne
    @MapsId("especialidad_id")
    @JoinColumn(name = "especialidad_id", foreignKey = @ForeignKey(name = "FK_MEDICO_ESPECIALIDAD"))
    private Especialidad especialidad;

    public void addEspecialidad(Especialidad nuevaEspecialidad){
        especialidad = nuevaEspecialidad;
    }
}
