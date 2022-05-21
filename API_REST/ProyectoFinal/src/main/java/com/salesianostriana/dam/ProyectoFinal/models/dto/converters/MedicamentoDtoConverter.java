package com.salesianostriana.dam.ProyectoFinal.models.dto.converters;

import com.salesianostriana.dam.ProyectoFinal.models.Medicamento;
import com.salesianostriana.dam.ProyectoFinal.models.dto.MedicamentoDto;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
public class MedicamentoDtoConverter {

    public MedicamentoDto medicamentoToMedicamentoDto(Medicamento medicamento){

        return MedicamentoDto.builder()
                .nombre(medicamento.getNombre())
                .descripcion(medicamento.getDescripcion())
                .build();
    }

    public Medicamento medicamentoDtoToMedicamento(MedicamentoDto medicamentoDto){
        return Medicamento.builder()
                .nombre(medicamentoDto.getNombre())
                .descripcion(medicamentoDto.getDescripcion())
                .build();
    }

}
