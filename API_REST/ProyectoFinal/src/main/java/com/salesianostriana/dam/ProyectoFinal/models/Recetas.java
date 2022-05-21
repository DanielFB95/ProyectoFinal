package com.salesianostriana.dam.ProyectoFinal.models;

import com.salesianostriana.dam.ProyectoFinal.models.enums.DiasDeTomas;
import com.salesianostriana.dam.ProyectoFinal.models.enums.MomentosDeTomas;
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

    @Column(name = "diasdetoma")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = DiasDeTomas.class)
    private List<DiasDeTomas> diasDeToma;

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

}
