package com.salesianostriana.dam.ProyectoFinal.controllers;

import com.salesianostriana.dam.ProyectoFinal.models.Especialidad;
import com.salesianostriana.dam.ProyectoFinal.models.Medico;
import com.salesianostriana.dam.ProyectoFinal.models.Paciente;
import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.MedicoDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.PacienteDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateMedicoDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetMedicoDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetPacienteDto;
import com.salesianostriana.dam.ProyectoFinal.services.MedicoService;
import com.salesianostriana.dam.ProyectoFinal.users.model.UserEntity;
import com.salesianostriana.dam.ProyectoFinal.utils.PaginationLinksUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("/medico")
@Tag(name = "MedicoController", description = "Controlador de medico")
public class MedicoController {

    private final MedicoService medicoService;
    private final MedicoDtoConverter medicoDtoConverter;
    private final PaginationLinksUtils paginationLinksUtils;
    private final PacienteDtoConverter pacienteDtoConverter;

    @Operation(summary = "Mostrar una m??dico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el m??dico .",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medico.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el m??dico.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medico.class))})})
    @GetMapping("/{id}")
    public ResponseEntity<GetMedicoDto> findOne(@PathVariable UUID id, @AuthenticationPrincipal UserEntity userEntity) {
        return ResponseEntity.ok().body(medicoService.findOne(id));
    }

    @Operation(summary = "Muestra todos los m??dicos.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Se han encontrado todos los m??dicos.",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medico.class))
            }),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado m??dicos",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Medico.class
                                    ))
                    })})
    @GetMapping("/")
    public ResponseEntity<Page<GetMedicoDto>> findAll (@AuthenticationPrincipal UserEntity userEntity, @PageableDefault(size = 10, page = 0) Pageable pageable, HttpServletRequest request){

        Page<Medico> data = medicoService.findAll(pageable);

        if(data.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{

            Page<GetMedicoDto> medicosDto = data.map(medicoDtoConverter::medicoToMedicoDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link",
                    paginationLinksUtils.createLinkHeader(medicosDto,uriBuilder)).body(medicosDto);
        }
    }

    @Operation(summary = "Editar un m??dico.")
    @ApiResponses( value = {@ApiResponse(responseCode = "200",
            description = "Se ha editado el m??dico.",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medico.class))
            }),
            @ApiResponse(responseCode = "404",
                    description = "No se ha podido editar el m??dico.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Medico.class
                                    ))
                    })
            ,
            @ApiResponse(responseCode = "401",
                    description = "No tiene permiso para realizar esta acci??n.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medico.class))})})
    @PutMapping("/{id}")
    public ResponseEntity<GetMedicoDto> edit (@PathVariable UUID id,@RequestBody CreateMedicoDto createMedicoDto, @AuthenticationPrincipal UserEntity userEntity){
        return ResponseEntity.ok().body(medicoService.edit(createMedicoDto, id));
    }

    @Operation(summary = "Borra un m??dico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado el m??dico.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medico.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el m??dico",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medico.class
                            ))
                    })
            ,
            @ApiResponse(responseCode = "401",
                    description = "No tiene permiso para realizar esta acci??n.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medico.class))})})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable UUID id, @AuthenticationPrincipal UserEntity userEntity){
        medicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Muestra todos los paciente de un m??dico.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Se han encontrado todos los pacientes del m??dico.",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medico.class))
            }),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado los pacientes del m??dico.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Medico.class
                                    ))
                    })})
    @GetMapping("/pacientes/{id}")
    public ResponseEntity<List<GetPacienteDto>> pacientesDeUnMedico(@AuthenticationPrincipal UserEntity userEntity, @PathVariable UUID id){
        return ResponseEntity.ok().body(medicoService.encontrarTodosLosPacientesDeUnMedico(id)
                .stream()
                .map(pacienteDtoConverter::pacienteToGetPacienteDto)
                .collect(Collectors.toList()));
    }
}
