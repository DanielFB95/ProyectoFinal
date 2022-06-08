package com.salesianostriana.dam.ProyectoFinal.services;

import com.salesianostriana.dam.ProyectoFinal.models.Medicamento;
import com.salesianostriana.dam.ProyectoFinal.models.dto.MedicamentoDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.MedicamentoDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.repositories.MedicamentoRepository;
import com.salesianostriana.dam.ProyectoFinal.services.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Esta clase define un servicio para la entidad Medicamento
 * @author Daniel Fernández
 */
@Service
@RequiredArgsConstructor
public class MedicamentoService extends BaseService<Medicamento,Long,MedicamentoRepository> {

    private final MedicamentoRepository medicamentoRepository;
    private final MedicamentoDtoConverter medicamentoDtoConverter;

    /**
     * Este método genera un nuevo medicamento
     * @param medicamentoDto
     * @return un objeto del tipo Medicamento
     */
    public Medicamento save(MedicamentoDto medicamentoDto){
        return medicamentoRepository.save(medicamentoDtoConverter.medicamentoDtoToMedicamento(medicamentoDto));
    }

    /**
     * Este método edita un medicamento
     * @param id
     * @param medicamentoDto
     * @return un objeto del tipo Optional<Medicamento>
     */
    public Optional<Medicamento> edit(Long id, MedicamentoDto medicamentoDto){
        return medicamentoRepository.findById(id).map(x -> {
            x.setNombre(medicamentoDto.getNombre());
            x.setDescripcion(medicamentoDto.getDescripcion());
            medicamentoRepository.save(x);
            return x;
        });
    }

    /**
     * Este método busca y muestra un medicamento por su id
     * @param id
     * @return un objeto del tipo MedicamentoDto
     */
    public MedicamentoDto findOne(Long id){
        return medicamentoDtoConverter.medicamentoToMedicamentoDto(
                medicamentoRepository.findById(id)
                        .orElseThrow(()-> new NotFoundException("No se ha encontrado el medicamento con el id: "+ id)));
    }

    /**
     * Este método devuelve una lista de medicamentos paginada
     * @param pageable
     * @return un objeto del tipo Page<Medicamento>
     */
    public Page<Medicamento> findAll(Pageable pageable){
        return medicamentoRepository.findAll(pageable);
    }

    /**
     * Este método borra un medicamento por su id
     * @param id
     */
    public void delete(Long id){
        medicamentoRepository.deleteById(id);
    }


}
