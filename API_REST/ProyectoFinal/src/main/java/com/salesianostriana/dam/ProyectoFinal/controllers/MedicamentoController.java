package com.salesianostriana.dam.ProyectoFinal.controllers;

import com.salesianostriana.dam.ProyectoFinal.models.Medicamento;
import com.salesianostriana.dam.ProyectoFinal.models.dto.MedicamentoDto;
import com.salesianostriana.dam.ProyectoFinal.repositories.MedicamentoRepository;
import com.salesianostriana.dam.ProyectoFinal.services.MedicamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medicamento")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoDto> findOne(@PathVariable Long id){
        return ResponseEntity.ok().body(medicamentoService.findOne(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<MedicamentoDto>> findAll(){
        return ResponseEntity.ok().body(medicamentoService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<Medicamento> save(@RequestBody MedicamentoDto medicamentoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoService.save(medicamentoDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> edit(@PathVariable Long id, @RequestBody MedicamentoDto medicamentoDto){
        return ResponseEntity.of(medicamentoService.edit(id, medicamentoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        medicamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
