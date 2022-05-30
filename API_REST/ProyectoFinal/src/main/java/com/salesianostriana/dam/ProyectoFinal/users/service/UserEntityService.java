package com.salesianostriana.dam.ProyectoFinal.users.service;

import com.salesianostriana.dam.ProyectoFinal.models.Especialidad;
import com.salesianostriana.dam.ProyectoFinal.models.Medico;
import com.salesianostriana.dam.ProyectoFinal.models.Paciente;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateMedicoDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreatePacienteDto;
import com.salesianostriana.dam.ProyectoFinal.repositories.EspecialidadRepository;
import com.salesianostriana.dam.ProyectoFinal.services.base.BaseService;
import com.salesianostriana.dam.ProyectoFinal.users.dto.CreateUserEntityDto;
import com.salesianostriana.dam.ProyectoFinal.users.model.UserEntity;
import com.salesianostriana.dam.ProyectoFinal.users.model.UserRole;
import com.salesianostriana.dam.ProyectoFinal.users.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.UUID;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserEntityService extends BaseService<UserEntity, UUID, UserEntityRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final EspecialidadRepository especialidadRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repository.findFirstByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email + " no encontrado."));
    }

    public UserEntity saveAdmin (CreateUserEntityDto userEntity){

        if(userEntity.getPassword().contentEquals(userEntity.getPassword2())){

            UserEntity admin = UserEntity.builder()
                    .nombre(userEntity.getNombre())
                    .apellidos(userEntity.getApellidos())
                    .email(userEntity.getEmail())
                    .telefono(userEntity.getTelefono())
                    .dni(userEntity.getDni())
                    .password(passwordEncoder.encode(userEntity.getPassword()))
                    .direccion(userEntity.getDireccion())
                    .rol(UserRole.ADMIN)
                    .build();

            return save(admin);
        }else {
            return null;
        }
    }

    public UserEntity saveMedico (CreateMedicoDto newUser){

        if(newUser.getPassword().contentEquals(newUser.getPassword2())){

            Especialidad especialidad = especialidadRepository.findById(newUser.getEspecialidad()).orElseThrow(() -> new RuntimeException());

            Medico medico = Medico.builder()
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .nombre(newUser.getNombre())
                    .apellidos(newUser.getApellidos())
                    .email(newUser.getEmail())
                    .telefono(newUser.getTelefono())
                    .dni(newUser.getDni())
                    .direccion(newUser.getDireccion())
                    .rol(UserRole.MEDICO)
                    .numColegiado(newUser.getNumColegiado())
                    .especialidad(especialidad)
                    .build();

            return save(medico);

        }else{
            return null;
        }
    }

    public UserEntity savePaciente (CreatePacienteDto newUser){

        //UserEntity medico = repository.findById(id).orElseThrow(() -> new NotFoundException("No se ha encontrado el médico con id: "+ id));

        if(newUser.getPassword().contentEquals(newUser.getPassword2())){

            UserEntity paciente = Paciente.builder()
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .nombre(newUser.getNombre())
                    .apellidos(newUser.getApellidos())
                    .email(newUser.getEmail())
                    .telefono(newUser.getTelefono())
                    .dni(newUser.getDni())
                    .direccion(newUser.getDireccion())
                    .rol(UserRole.PACIENTE)
                    .observaciones(newUser.getObservaciones())
                    .build();

            return save(paciente);
        }else{
            return null;
        }
    }
}
