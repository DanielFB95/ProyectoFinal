package com.salesianostriana.dam.ProyectoFinal.controllers;

import com.salesianostriana.dam.ProyectoFinal.models.Especialidad;
import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.EspecialidadDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateEspecialidadDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetEspecilidadDto;
import com.salesianostriana.dam.ProyectoFinal.services.EspecialidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/especialidad")
@RequiredArgsConstructor
public class EspecialidadController {

    private final EspecialidadDtoConverter especialidadDtoConverter;
    private final EspecialidadService especialidadService;

    @PostMapping("/")
    public ResponseEntity<Especialidad> crearEspecialidad(@RequestBody CreateEspecialidadDto createEspecialidadDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(especialidadService.save(createEspecialidadDto));
    }

}
