package com.salesianostriana.dam.ProyectoFinal.controllers;

import com.salesianostriana.dam.ProyectoFinal.models.Especialidad;
import com.salesianostriana.dam.ProyectoFinal.models.Medicamento;
import com.salesianostriana.dam.ProyectoFinal.models.dto.MedicamentoDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.MedicamentoDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.services.MedicamentoService;
import com.salesianostriana.dam.ProyectoFinal.users.model.UserEntity;
import com.salesianostriana.dam.ProyectoFinal.utils.PaginationLinksUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/medicamento")
@Tag(name="MedicamentoController", description = "Controlador de medicamento")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;
    private final PaginationLinksUtils paginationLinksUtils;
    private final MedicamentoDtoConverter medicamentoDtoConverter;

    @Operation(summary = "Mostrar un medicamento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el medicamento .",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Especialidad.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna especialidad.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Especialidad.class))})})
    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoDto> findOne(@PathVariable Long id, @AuthenticationPrincipal UserEntity userEntity){
        return ResponseEntity.ok().body(medicamentoService.findOne(id));
    }

    @Operation(summary = "Muestra todas los medicamentos.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Se han encontrado todos los medicamentos.",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medicamento.class))
            }),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado medicamentos",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Medicamento.class
                                    ))
                    })})
    @GetMapping("/")
    public ResponseEntity<Page<MedicamentoDto>> findAll(@AuthenticationPrincipal UserEntity userEntity, @PageableDefault(size = 10, page = 0) Pageable pageable, HttpServletRequest request){

        Page<Medicamento> data = medicamentoService.findAll(pageable);

        if(data.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{

            Page<MedicamentoDto> medicamentosDto = data.map(medicamentoDtoConverter::medicamentoToMedicamentoDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link",
                    paginationLinksUtils.createLinkHeader(medicamentosDto,uriBuilder)).body(medicamentosDto);
        }
    }

    @Operation(summary = "Registra un nuevo medicamento")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Se ha creado el medicamento.",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medicamento.class))
            }),
            @ApiResponse(responseCode = "404",
                    description = "No se ha creado el medicamento.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Medicamento.class
                                    ))
                    })
            ,
            @ApiResponse(responseCode = "401",
                    description = "No tiene permiso para realizar esta acción.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medicamento.class))})})

    @PostMapping("/")
    public ResponseEntity<Medicamento> save(@Valid @RequestBody MedicamentoDto medicamentoDto,@AuthenticationPrincipal UserEntity userEntity){
        return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoService.save(medicamentoDto));
    }

    @Operation(summary = "Editar un medicamento.")
    @ApiResponses( value = {@ApiResponse(responseCode = "200",
            description = "Se ha editado el medicamento.",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medicamento.class))
            }),
            @ApiResponse(responseCode = "404",
                    description = "No se ha podido editar el medicamento.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Medicamento.class
                                    ))
                    })
            ,
            @ApiResponse(responseCode = "401",
                    description = "No tiene permiso para realizar esta acción.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medicamento.class))})})

    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> edit(@PathVariable Long id, @RequestBody MedicamentoDto medicamentoDto,@AuthenticationPrincipal UserEntity userEntity){
        return ResponseEntity.of(medicamentoService.edit(id, medicamentoDto));
    }

    @Operation(summary = "Borra un medicamento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado el medicamento.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medicamento.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el medicamento",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medicamento.class
                            ))
                    })
            ,
            @ApiResponse(responseCode = "401",
                    description = "No tiene permiso para realizar esta acción.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medicamento.class))})})

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id,@AuthenticationPrincipal UserEntity userEntity){
        medicamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
