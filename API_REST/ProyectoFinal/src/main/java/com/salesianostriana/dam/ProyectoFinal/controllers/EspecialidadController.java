package com.salesianostriana.dam.ProyectoFinal.controllers;

import com.salesianostriana.dam.ProyectoFinal.models.Especialidad;
import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.EspecialidadDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateEspecialidadDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetEspecilidadDto;
import com.salesianostriana.dam.ProyectoFinal.services.EspecialidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/especialidad")
@RequiredArgsConstructor
public class EspecialidadController {

    private final EspecialidadDtoConverter especialidadDtoConverter;
    private final EspecialidadService especialidadService;

    @PostMapping("/")
    public ResponseEntity<Especialidad> create(@RequestBody CreateEspecialidadDto createEspecialidadDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(especialidadService.save(createEspecialidadDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especialidad> edit(@PathVariable Long id, @RequestBody CreateEspecialidadDto nuevaEspecialidad){

        return ResponseEntity.ok().body(especialidadService.edit(id, nuevaEspecialidad));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetEspecilidadDto> findOne(@PathVariable Long id){
        return ResponseEntity.ok().body(especialidadService.findOne(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<GetEspecilidadDto>> findAll(){
        return ResponseEntity.ok().body(especialidadService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        especialidadService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
