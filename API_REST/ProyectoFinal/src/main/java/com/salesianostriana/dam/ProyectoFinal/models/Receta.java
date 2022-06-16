package com.salesianostriana.dam.ProyectoFinal.models;

import com.salesianostriana.dam.ProyectoFinal.models.enums.DiasDeTomas;
import com.salesianostriana.dam.ProyectoFinal.models.enums.MomentosDeTomas;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase define la entidad Receta
 * @author Daniel Fernández
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
public class Receta implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fechainicio")
    private LocalDate fechaInicio;

    @Column(name = "fechafin")
    private LocalDate fechaFin;

    @Column(name = "diasdetoma")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = DiasDeTomas.class)
    private List<DiasDeTomas> diasDeTomas;

    @Column(name = "momentosdetoma")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = MomentosDeTomas.class)
    private List<MomentosDeTomas> momentosDeToma;

    @ManyToOne
    @JoinColumn(name = "medicamento_id", foreignKey = @ForeignKey(name = "FK_RECETA_MEDICAMENTO"))
    private Medicamento medicamento;

    @ManyToOne
    @JoinColumn(name = "medico_id", foreignKey = @ForeignKey(name = "FK_RECETAS_MEDICO"))
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id", foreignKey = @ForeignKey(name = "FK_RECETAS_PACIENTE"))
    private Paciente paciente;

    //Helpers

    public void addMedico(Medico nuevoMedico){
        this.medico = nuevoMedico;
    }

    public void addPaciente(Paciente nuevoPaciente){
        this.paciente = nuevoPaciente;
    }

    public void addMedicamento(Medicamento nuevoMedicamento){
        this.medicamento = nuevoMedicamento;
    }

    public void deleteMedico(){
        this.medico = null;
    }

    public void deletePaciente(){
        this.paciente = null;
    }

    public void deleteMedicamento(){
        this.medicamento = null;
    }
}
