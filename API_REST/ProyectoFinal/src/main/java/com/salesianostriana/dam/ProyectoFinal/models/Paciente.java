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
    private Medico medico;

    //Helper

    public void addMedico(Medico nuevoMedico){
        this.medico = nuevoMedico;
    }
}
