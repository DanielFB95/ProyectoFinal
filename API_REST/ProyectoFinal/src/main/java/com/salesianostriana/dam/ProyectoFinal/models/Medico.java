package com.salesianostriana.dam.ProyectoFinal.models;

import com.salesianostriana.dam.ProyectoFinal.users.model.UserEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@SuperBuilder
public class Medico extends UserEntity implements Serializable {

    @Column(name = "numcolegiado")
    private String numColegiado;

    @OneToOne
    // @MapsId("especialidad_id")
    @JoinColumn(name = "especialidad_id", foreignKey = @ForeignKey(name = "FK_MEDICO_ESPECIALIDAD"))
    private Especialidad especialidad;

    /*@Builder.Default
    @OneToMany(mappedBy = "medico", orphanRemoval = true)
    private List<Paciente> pacientes = new ArrayList<>();*/

    //HELPERS

    public void addEspecialidad(Especialidad nuevaEspecialidad){
        especialidad = nuevaEspecialidad;
    }

    public void removeEspecialidadFromMedico(Medico medico){
        especialidad = null;
    }
}
