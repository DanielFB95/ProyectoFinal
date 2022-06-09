package com.salesianostriana.dam.ProyectoFinal.controllers;

import com.salesianostriana.dam.ProyectoFinal.models.Medico;
import com.salesianostriana.dam.ProyectoFinal.models.Paciente;
import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.PacienteDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.RecetaDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreatePacienteDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetPacienteDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetRecetaDto;
import com.salesianostriana.dam.ProyectoFinal.services.PacienteService;
import com.salesianostriana.dam.ProyectoFinal.users.model.UserEntity;
import com.salesianostriana.dam.ProyectoFinal.users.service.UserEntityService;
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
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/paciente")
@Tag(name="PacienteController", description = "Controlador de paciente")
public class PacienteController {

    private final UserEntityService userEntityService;
    private final PacienteDtoConverter pacienteDtoConverter;
    private final PacienteService pacienteService;
    private final PaginationLinksUtils paginationLinksUtils;
    private final RecetaDtoConverter recetaDtoConverter;

    @Operation(summary = "Mostrar un paciente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el paciente .",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Paciente.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el paciente.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Paciente.class))})})
    @GetMapping("/{id}")
    public ResponseEntity<GetPacienteDto> findOne(@PathVariable UUID id){
        return ResponseEntity.ok().body(pacienteService.findOne(id));
    }

    @Operation(summary = "Muestra todos los pacientes.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Se han encontrado todos los pacientes.",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Paciente.class))
            }),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado pacientes",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Paciente.class
                                    ))
                    })})
    @GetMapping("/")
    public ResponseEntity<Page<GetPacienteDto>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable, HttpServletRequest request){

        Page<Paciente> data = pacienteService.findAll(pageable);

        if(data.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{

            Page<GetPacienteDto> pacientesDto = data.map(pacienteDtoConverter::pacienteToGetPacienteDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link",
                    paginationLinksUtils.createLinkHeader(pacientesDto,uriBuilder)).body(pacientesDto);
        }
    }

    @Operation(summary = "Editar un paciente.")
    @ApiResponses( value = {@ApiResponse(responseCode = "200",
            description = "Se ha editado el paciente.",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Paciente.class))
            }),
            @ApiResponse(responseCode = "404",
                    description = "No se ha podido editar el paciente.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Paciente.class
                                    ))
                    })
            ,
            @ApiResponse(responseCode = "401",
                    description = "No tiene permiso para realizar esta acción.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Paciente.class))})})
    @PutMapping("/{id}")
    public ResponseEntity<GetPacienteDto> edit (@PathVariable UUID id, @RequestBody CreatePacienteDto createPacienteDto){
        return ResponseEntity.ok().body(pacienteService.edit(createPacienteDto, id));
    }

    @Operation(summary = "Borra un paciente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado el paciente.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Paciente.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el paciente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Paciente.class
                            ))
                    })
            ,
            @ApiResponse(responseCode = "401",
                    description = "No tiene permiso para realizar esta acción.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Paciente.class))})})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        pacienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Muestra todas las recetas de un paciente.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Se han encontrado todas las recetas del paciente.",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Paciente.class))
            }),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado las recetas del paciente",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Paciente.class
                                    ))
                    })})
    @GetMapping("/recetas/{id}")
    public ResponseEntity<List<GetRecetaDto>> recetasDeUnPaciente(@AuthenticationPrincipal UserEntity userEntity, @PathVariable UUID id){
        return ResponseEntity.ok().body(
            pacienteService.encontrarLasRecetasDeUnPaciente(id)
                    .stream()
                    .map(recetaDtoConverter::recetaToRecetaDto)
                    .collect(Collectors.toList())
        );
    }
}
