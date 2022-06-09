package com.salesianostriana.dam.ProyectoFinal.models.dto.converters;

import com.salesianostriana.dam.ProyectoFinal.models.Medicamento;
import com.salesianostriana.dam.ProyectoFinal.models.dto.MedicamentoDto;
import lombok.*;
import org.springframework.stereotype.Component;

/**
 * Esta clase define un convertidor entre dtos de la entidad Medicamento
 * @author Daniel Fernández
 */
@Component
public class MedicamentoDtoConverter {

    /**
     *
     * @param medicamento
     * @return un objeto del tipo MedicamentoDto
     */
    public MedicamentoDto medicamentoToMedicamentoDto(Medicamento medicamento){

        return MedicamentoDto.builder()
                .id(medicamento.getId())
                .nombre(medicamento.getNombre())
                .descripcion(medicamento.getDescripcion())
                .build();
    }

    /**
     *
     * @param medicamentoDto
     * @return un objeto del tipo Medicamento
     */
    public Medicamento medicamentoDtoToMedicamento(MedicamentoDto medicamentoDto){
        return Medicamento.builder()
                .id(medicamentoDto.getId())
                .nombre(medicamentoDto.getNombre())
                .descripcion(medicamentoDto.getDescripcion())
                .build();
    }

}
