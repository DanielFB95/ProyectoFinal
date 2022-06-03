package com.salesianostriana.dam.ProyectoFinal.controllers;

import com.salesianostriana.dam.ProyectoFinal.models.Especialidad;
import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.EspecialidadDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateEspecialidadDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetEspecialidadDto;
import com.salesianostriana.dam.ProyectoFinal.services.EspecialidadService;
import com.salesianostriana.dam.ProyectoFinal.users.model.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/especialidad")
@RequiredArgsConstructor
@Tag(name= "Especialidad", description = "Controlador de especialidad")
public class EspecialidadController {

    private final EspecialidadDtoConverter especialidadDtoConverter;
    private final EspecialidadService especialidadService;

    @Operation(summary = "Registra una nueva especialidad")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Se ha creado la especialidad.",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Especialidad.class))
            }),
            @ApiResponse(responseCode = "404",
                    description = "No se ha creado la especialidad.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Especialidad.class
                                    ))
                    })
            ,
            @ApiResponse(responseCode = "401",
                    description = "No tiene permiso para realizar esta acción.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Especialidad.class))})})
    @PostMapping("/")
    public ResponseEntity<GetEspecialidadDto> create(@RequestBody CreateEspecialidadDto createEspecialidadDto, @AuthenticationPrincipal UserEntity userEntity){

            return ResponseEntity.status(HttpStatus.CREATED).body(especialidadDtoConverter.especialidadToGetEspecialidadDto(especialidadService.save(createEspecialidadDto)));
    }

    @Operation(summary = "Editar una especialidad.")
    @ApiResponses( value = {@ApiResponse(responseCode = "200",
            description = "Se ha editado la especialidad.",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Especialidad.class))
            }),
            @ApiResponse(responseCode = "404",
                    description = "No se ha podido editar la especialidad.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Especialidad.class
                                    ))
                    })
            ,
            @ApiResponse(responseCode = "401",
                    description = "No tiene permiso para realizar esta acción.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Especialidad.class))})})
    @PutMapping("/{id}")
    public ResponseEntity<GetEspecialidadDto> edit(@PathVariable Long id, @RequestBody CreateEspecialidadDto nuevaEspecialidad, @AuthenticationPrincipal UserEntity userEntity){

            return ResponseEntity.ok().body(especialidadDtoConverter.especialidadToGetEspecialidadDto(especialidadService.edit(id, nuevaEspecialidad)));

    }

    @Operation(summary = "Mostrar una especialidad.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la especialidad .",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Especialidad.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna especialidad.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Especialidad.class))})})
    @GetMapping("/{id}")
    public ResponseEntity<GetEspecialidadDto> findOne(@PathVariable Long id, @AuthenticationPrincipal UserEntity userEntity){
        return ResponseEntity.ok().body(especialidadService.findOne(id));
    }

    @Operation(summary = "Muestra todas las especialidades.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Se han encontrado todas las especialidades.",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Especialidad.class))
            }),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado especialidades",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Especialidad.class
                                    ))
                    })})
    @GetMapping("/")
    public ResponseEntity<List<GetEspecialidadDto>> findAll(@AuthenticationPrincipal UserEntity userEntity){
        return ResponseEntity.ok().body(especialidadService.findAll());
    }

    @Operation(summary = "Borra un especialidad.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado la especialidad.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Especialidad.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la especialidad",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Especialidad.class
                            ))
                    })
            ,
            @ApiResponse(responseCode = "401",
                    description = "No tiene permiso para realizar esta acción.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Especialidad.class))})})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @AuthenticationPrincipal UserEntity userEntity){
        especialidadService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
