package com.salesianostriana.dam.ProyectoFinal.users.controller;

import com.salesianostriana.dam.ProyectoFinal.models.Medico;
import com.salesianostriana.dam.ProyectoFinal.models.Paciente;
import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.MedicoDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.models.dto.converters.PacienteDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateMedicoDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreatePacienteDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetMedicoDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.gets.GetPacienteDto;
import com.salesianostriana.dam.ProyectoFinal.users.dto.CreateUserEntityDto;
import com.salesianostriana.dam.ProyectoFinal.users.dto.GetUserEntityDto;
import com.salesianostriana.dam.ProyectoFinal.users.dto.UserEntityDtoConverter;
import com.salesianostriana.dam.ProyectoFinal.users.model.UserEntity;
import com.salesianostriana.dam.ProyectoFinal.users.service.UserEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "Usuario", description = "Controller usuario")
public class UserEntityController {

    private final UserEntityService userEntityService;
    private final UserEntityDtoConverter userEntityDtoConverter;
    private final MedicoDtoConverter medicoDtoConverter;
    private final PacienteDtoConverter pacienteDtoConverter;


    @Operation(summary = "Registra un nuevo medico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han registrado un medico.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha registrado ningún medico.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))})
            ,
            @ApiResponse(responseCode = "401",
                    description = "No tiene permiso para realizar esta acción.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medico.class))})})
    @PostMapping("/admin")
    public GetUserEntityDto saveAdmin(@RequestBody CreateUserEntityDto createUserEntityDto){
        return userEntityDtoConverter.UserEntityToGetUserEntityDto(
                userEntityService.saveAdmin(createUserEntityDto));
    }


    @Operation(summary = "Registra un nuevo medico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han registrado un medico.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha registrado ningún medico.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))})
            ,
            @ApiResponse(responseCode = "401",
                    description = "No tiene permiso para realizar esta acción.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medico.class))})})
    @PostMapping("/medico")
    public ResponseEntity<GetMedicoDto> nuevoMedico(@RequestBody CreateMedicoDto nuevoMedico, @AuthenticationPrincipal UserEntity userEntity/*, @RequestPart("file")MultipartFile file*/) throws Exception {

        Medico user = (Medico) userEntityService.saveMedico(nuevoMedico /*file*/);

        if (user == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(medicoDtoConverter.medicoToMedicoDto(user));
        }
    }


    @Operation(summary = "Registra un nuevo paciente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han registrado un paciente.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha registrado ningún paciente.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))})
            ,
            @ApiResponse(responseCode = "401",
                    description = "No tiene permiso para realizar esta acción.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Paciente.class))})})
    @PostMapping("/paciente")
    public ResponseEntity<GetPacienteDto> nuevoPaciente(@RequestBody CreatePacienteDto nuevoPaciente, @AuthenticationPrincipal UserEntity userEntity) {

        Paciente user = (Paciente) userEntityService.savePaciente(nuevoPaciente, userEntity);

        if (user == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(pacienteDtoConverter.pacienteToGetPacienteDto(user));
        }
    }



}
