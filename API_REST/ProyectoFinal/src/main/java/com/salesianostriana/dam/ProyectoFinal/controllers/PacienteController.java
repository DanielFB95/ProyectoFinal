package com.salesianostriana.dam.ProyectoFinal.controllers;

import com.salesianostriana.dam.ProyectoFinal.models.Paciente;
import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.PacienteDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreatePacienteDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetPacienteDto;
import com.salesianostriana.dam.ProyectoFinal.services.PacienteService;
import com.salesianostriana.dam.ProyectoFinal.users.service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/paciente")
public class PacienteController {

    private final UserEntityService userEntityService;
    private final PacienteDtoConverter pacienteDtoConverter;
    private final PacienteService pacienteService;

    @GetMapping("/{id}")
    public ResponseEntity<GetPacienteDto> findOne(@PathVariable UUID id){
        return ResponseEntity.ok().body(pacienteService.findOne(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<GetPacienteDto>> findAll(){
        return ResponseEntity.ok().body(pacienteService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetPacienteDto> edit (@PathVariable UUID id, @RequestBody CreatePacienteDto createPacienteDto){
        return ResponseEntity.ok().body(pacienteService.edit(createPacienteDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        pacienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
