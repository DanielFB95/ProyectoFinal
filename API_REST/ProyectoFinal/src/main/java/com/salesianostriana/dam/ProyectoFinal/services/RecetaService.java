package com.salesianostriana.dam.ProyectoFinal.services;

import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.RecetaDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateRecetaDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetRecetaDto;
import com.salesianostriana.dam.ProyectoFinal.repositories.MedicamentoRepository;
import com.salesianostriana.dam.ProyectoFinal.repositories.MedicoRepository;
import com.salesianostriana.dam.ProyectoFinal.repositories.PacienteRepository;
import com.salesianostriana.dam.ProyectoFinal.repositories.RecetasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecetaService {

    private final RecetasRepository recetasRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicamentoRepository medicamentoRepository;
    private final RecetaDtoConverter recetaDtoConverter;

    public GetRecetaDto findOne(Long id){
        return recetaDtoConverter.recetaToRecetaDto(recetasRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("No se ha encontrado la receta")));
    }

    public List<GetRecetaDto> findAll(){
        return recetasRepository.findAll().stream()
                .map(recetaDtoConverter::recetaToRecetaDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id){
        recetasRepository.deleteById(id);
    }

    public GetRecetaDto edit(CreateRecetaDto createRecetaDto, Long id){

        return recetaDtoConverter.recetaToRecetaDto(
                recetasRepository.findById(id).map(
                        x -> {
                            x.setFechaFin(createRecetaDto.getFechaFin());
                            x.setFechaInicio(createRecetaDto.getFechaInicio());
                            x.setDiasDeToma(createRecetaDto.getDiasDeTomas());
                            x.setMomentosDeToma(createRecetaDto.getMomentosDeTomas());
                            recetasRepository.save(x);
                            return x;
                        }
                ).orElseThrow(()-> new NotFoundException("No se ha encontrado la receta"))
        );
    }

}
