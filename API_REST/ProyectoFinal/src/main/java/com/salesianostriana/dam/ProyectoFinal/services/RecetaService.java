package com.salesianostriana.dam.ProyectoFinal.services;

import com.salesianostriana.dam.ProyectoFinal.models.Medicamento;
import com.salesianostriana.dam.ProyectoFinal.models.Medico;
import com.salesianostriana.dam.ProyectoFinal.models.Paciente;
import com.salesianostriana.dam.ProyectoFinal.models.Receta;
import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.RecetaDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateRecetaDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetRecetaDto;
import com.salesianostriana.dam.ProyectoFinal.repositories.MedicamentoRepository;
import com.salesianostriana.dam.ProyectoFinal.repositories.MedicoRepository;
import com.salesianostriana.dam.ProyectoFinal.repositories.PacienteRepository;
import com.salesianostriana.dam.ProyectoFinal.repositories.RecetasRepository;
import com.salesianostriana.dam.ProyectoFinal.services.base.BaseService;
import com.salesianostriana.dam.ProyectoFinal.users.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecetaService extends BaseService<Receta,Long,RecetasRepository> {

    private final RecetasRepository recetasRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicamentoRepository medicamentoRepository;
    private final RecetaDtoConverter recetaDtoConverter;

    public Receta save(CreateRecetaDto createRecetaDto, UserEntity userEntityMedico){
        Paciente paciente = pacienteRepository.findById(createRecetaDto.getIdPaciente()).orElseThrow(()-> new NotFoundException("No se ha encontrado el paciente"));
        Medico medico = medicoRepository.findById(userEntityMedico.getId()).orElseThrow(()-> new NotFoundException("No se ha encontrado el médico."));
        Medicamento medicamento = medicamentoRepository.findById(createRecetaDto.getIdMedicamento()).orElseThrow(()-> new NotFoundException("No se ha encontrado el medicamento"));
        Receta receta = Receta.builder()
                .momentosDeToma(createRecetaDto.getMomentosDeTomas())
                .diasDeTomas(createRecetaDto.getDiasDeTomas())
                .fechaFin(createRecetaDto.getFechaFin())
                .fechaInicio(createRecetaDto.getFechaInicio())
                .build();

        receta.addPaciente(paciente);
        receta.addMedicamento(medicamento);
        receta.addMedico(medico);

        recetasRepository.save(receta);
        return receta;
    }

    public GetRecetaDto findOne(Long id){
        return recetaDtoConverter.recetaToRecetaDto(recetasRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("No se ha encontrado la receta")));
    }

    public Page<Receta> findAll(Pageable pageable){
        return recetasRepository.findAll(pageable);
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
                            x.setDiasDeTomas(createRecetaDto.getDiasDeTomas());
                            x.setMomentosDeToma(createRecetaDto.getMomentosDeTomas());
                            recetasRepository.save(x);
                            return x;
                        }
                ).orElseThrow(()-> new NotFoundException("No se ha encontrado la receta"))
        );
    }

}
