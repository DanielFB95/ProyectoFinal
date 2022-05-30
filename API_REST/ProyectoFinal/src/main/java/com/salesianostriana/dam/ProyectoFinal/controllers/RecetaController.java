package com.salesianostriana.dam.ProyectoFinal.controllers;

import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateRecetaDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetRecetaDto;
import com.salesianostriana.dam.ProyectoFinal.services.RecetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/receta")
public class RecetaController {

    private final RecetaService recetaService;

    @GetMapping("/{id}")
    public ResponseEntity<GetRecetaDto> findOne(@PathVariable Long id){
        return ResponseEntity.ok().body(recetaService.findOne(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<GetRecetaDto>> findAll(){
        return ResponseEntity.ok().body(recetaService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetRecetaDto> edit(@PathVariable Long id, @RequestBody CreateRecetaDto createRecetaDto){
        return ResponseEntity.ok().body(recetaService.edit(createRecetaDto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        recetaService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
