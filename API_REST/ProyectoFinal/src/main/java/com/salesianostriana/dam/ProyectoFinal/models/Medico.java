package com.salesianostriana.dam.ProyectoFinal.models;

import com.salesianostriana.dam.ProyectoFinal.users.model.UserEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase define la entidad Medico que hereda de UserEntity
 * @author Daniel Fernández
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@SuperBuilder
public class Medico extends UserEntity implements Serializable {

    @Column(name = "numcolegiado")
    private String numColegiado;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especialidad_id", foreignKey = @ForeignKey(name = "FK_MEDICO_ESPECIALIDAD"))
    private Especialidad especialidad;

    //HELPERS

    public void addEspecialidad(Especialidad nuevaEspecialidad){
        especialidad = nuevaEspecialidad;
    }

    public void removeEspecialidadFromMedico(){
        this.especialidad = null;
    }

}
