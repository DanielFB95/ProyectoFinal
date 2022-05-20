package com.salesianostriana.dam.ProyectoFinal.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
public class Recetas {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fechainicio")
    private LocalDateTime fechaInicio;
    @Column(name = "fechafin")
    private LocalDateTime fechaFin;

    @Builder.Default
    @Access(AccessType.PROPERTY)
    @Column(name = "diasdetoma")
    @ElementCollection(targetClass=String.class)
    private List<String> diasDeToma = new ArrayList<>();

    @Builder.Default
    @Access(AccessType.PROPERTY)
    @Column(name = "momentosdetoma")
    @ElementCollection(targetClass=String.class)
    private List<String> momentosDeToma = new ArrayList<>();

    @ManyToOne
/*    @MapsId("medicamento_id")*/
    @JoinColumn(name = "medicamento_id", foreignKey = @ForeignKey(name = "FK_RECETA_MEDICAMENTO"))
    private Medicamento medicamento;

    @ManyToOne
/*    @MapsId("medico_id")*/
    @JoinColumn(name = "medico_id", foreignKey = @ForeignKey(name = "FK_RECETAS_MEDICO"))
    private Medico medico;

    @ManyToOne
/*    @MapsId("pacient_id")*/
    @JoinColumn(name = "paciente_id", foreignKey = @ForeignKey(name = "FK_RECETAS_PACIENTE"))
    private Paciente paciente;

}
