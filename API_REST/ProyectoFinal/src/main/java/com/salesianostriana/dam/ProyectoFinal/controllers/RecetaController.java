package com.salesianostriana.dam.ProyectoFinal.controllers;

import com.salesianostriana.dam.ProyectoFinal.models.Receta;
import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.RecetaDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateRecetaDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetRecetaDto;
import com.salesianostriana.dam.ProyectoFinal.services.RecetaService;
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
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/receta")
@Tag(name = "RecetaController", description = "Controlador de receta")
public class RecetaController {

    private final RecetaService recetaService;
    private final RecetaDtoConverter recetaDtoConverter;
    private final PaginationLinksUtils paginationLinksUtils;

    @Operation(summary = "Registra una nueva receta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha registrado una receta.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Receta.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha registrado ninguna receta.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Receta.class))})
            ,
            @ApiResponse(responseCode = "401",
                    description = "No tiene permiso para realizar esta acción.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Receta.class))})})
    @PostMapping("/")
    public ResponseEntity<GetRecetaDto> save(@Valid @RequestBody CreateRecetaDto createRecetaDto, @AuthenticationPrincipal UserEntity userEntity){
        return ResponseEntity.status(HttpStatus.CREATED).body(recetaDtoConverter.recetaToRecetaDto(recetaService.save(createRecetaDto,userEntity)));
    }

    @Operation(summary = "Mostrar una receta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la receta.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Receta.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la receta.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Receta.class))})})
    @GetMapping("/{id}")
    public ResponseEntity<GetRecetaDto> findOne(@PathVariable Long id){
        return ResponseEntity.ok().body(recetaService.findOne(id));
    }

    @Operation(summary = "Muestra todas las recetas.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Se han encontrado todas las recetas.",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Receta.class))
            }),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado recetas",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Receta.class
                                    ))
                    })})
    @GetMapping("/")
    public ResponseEntity<Page<GetRecetaDto>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable, HttpServletRequest request){

        Page<Receta> data = recetaService.findAll(pageable);

        if(data.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{

            Page<GetRecetaDto> recetasDto = data.map(recetaDtoConverter::recetaToRecetaDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link",
                    paginationLinksUtils.createLinkHeader(recetasDto,uriBuilder)).body(recetasDto);
        }


    }


    @Operation(summary = "Editar una receta.")
    @ApiResponses( value = {@ApiResponse(responseCode = "200",
            description = "Se ha editado la receta.",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Receta.class))
            }),
            @ApiResponse(responseCode = "404",
                    description = "No se ha podido editar la receta.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Receta.class
                                    ))
                    })
            ,
            @ApiResponse(responseCode = "401",
                    description = "No tiene permiso para realizar esta acción.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Receta.class))})})
    @PutMapping("/{id}")
    public ResponseEntity<GetRecetaDto> edit(@PathVariable Long id, @RequestBody CreateRecetaDto createRecetaDto){
        return ResponseEntity.ok().body(recetaService.edit(createRecetaDto,id));
    }

    @Operation(summary = "Borra una receta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado la receta.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Receta.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la receta",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Receta.class
                            ))
                    })
            ,
            @ApiResponse(responseCode = "401",
                    description = "No tiene permiso para realizar esta acción.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Receta.class))})})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        recetaService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
