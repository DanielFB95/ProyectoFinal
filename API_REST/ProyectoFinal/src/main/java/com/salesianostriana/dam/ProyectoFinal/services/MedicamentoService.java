package com.salesianostriana.dam.ProyectoFinal.services;

import com.salesianostriana.dam.ProyectoFinal.models.Medicamento;
import com.salesianostriana.dam.ProyectoFinal.models.dto.MedicamentoDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.MedicamentoDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.repositories.MedicamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;
    private final MedicamentoDtoConverter medicamentoDtoConverter;

    public Medicamento save(MedicamentoDto medicamentoDto){
        return medicamentoRepository.save(medicamentoDtoConverter.medicamentoDtoToMedicamento(medicamentoDto));
    }

    public Optional<Medicamento> edit(Long id, MedicamentoDto medicamentoDto){
        return medicamentoRepository.findById(id).map(x -> {
            x.setNombre(medicamentoDto.getNombre());
            x.setDescripcion(medicamentoDto.getDescripcion());
            medicamentoRepository.save(x);
            return x;
        });
    }

    public MedicamentoDto findOne(Long id){
        return medicamentoDtoConverter.medicamentoToMedicamentoDto(
                medicamentoRepository.findById(id)
                        .orElseThrow(()-> new NotFoundException("No se ha encontrado el medicamento con el id: "+ id)));
    }

    public List<MedicamentoDto> findAll(){
        return medicamentoRepository.findAll().stream()
                .map(medicamentoDtoConverter::medicamentoToMedicamentoDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id){
        medicamentoRepository.deleteById(id);
    }


}
