package com.salesianostriana.dam.ProyectoFinal.controllers;

import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.MedicoDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateMedicoDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetMedicoDto;
import com.salesianostriana.dam.ProyectoFinal.services.MedicoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medico")
public class MedicoController {

    private final MedicoService medicoService;

    @GetMapping("/{id}")
    public ResponseEntity<GetMedicoDto> findOne(@PathVariable UUID id) {
        return ResponseEntity.ok().body(medicoService.findOne(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<GetMedicoDto>> findAll (){
        return ResponseEntity.ok().body(medicoService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetMedicoDto> edit (@PathVariable UUID id,@RequestBody CreateMedicoDto createMedicoDto){
        return ResponseEntity.ok().body(medicoService.edit(createMedicoDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable UUID id){
        medicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
