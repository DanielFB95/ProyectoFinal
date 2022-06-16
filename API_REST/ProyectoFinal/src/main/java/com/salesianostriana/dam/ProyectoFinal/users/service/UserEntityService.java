package com.salesianostriana.dam.ProyectoFinal.users.service;

import com.salesianostriana.dam.ProyectoFinal.errors.exceptions.StorageException;
import com.salesianostriana.dam.ProyectoFinal.models.Especialidad;
import com.salesianostriana.dam.ProyectoFinal.models.Medico;
import com.salesianostriana.dam.ProyectoFinal.models.Paciente;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreateMedicoDto;
import com.salesianostriana.dam.ProyectoFinal.models.dto.create.CreatePacienteDto;
import com.salesianostriana.dam.ProyectoFinal.repositories.EspecialidadRepository;
import com.salesianostriana.dam.ProyectoFinal.repositories.MedicoRepository;
import com.salesianostriana.dam.ProyectoFinal.services.FileSystemStorageService;
import com.salesianostriana.dam.ProyectoFinal.services.ImageScalerService;
import com.salesianostriana.dam.ProyectoFinal.services.StorageService;
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
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.webjars.NotFoundException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.UUID;

/**
 * Esta clase define un servicio para la entidad UserEntity
 * @author Daniel Fernández
 */
@Service("userDetailsService")
@RequiredArgsConstructor
public class UserEntityService extends BaseService<UserEntity, UUID, UserEntityRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final EspecialidadRepository especialidadRepository;
    private final MedicoRepository medicoRepository;
    private final StorageService storageService;
    private final ImageScalerService imageScaler;


    /**
     * Este método carga un usuario por su email
     * @param email
     * @return un objeto del tipo UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repository.findFirstByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email + " no encontrado."));
    }

    /**
     * Este método genera un nuevo Administrador
     * @param userEntity
     * @return un objeto del tipo UserEntity
     */
    public UserEntity saveAdmin (CreateUserEntityDto userEntity){

        if(userEntity.getPassword().contentEquals(userEntity.getPassword2())){

            UserEntity admin = UserEntity.builder()
                    .nombre(userEntity.getNombre())
                    .apellidos(userEntity.getApellidos())
                    .fechaNacimiento(userEntity.getFechaNacimiento())
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

    /**
     * Este método genera un nuevo médico
     * @param newUser
     * @param avatar
     * @return un objeto del tipo UserEntity
     * @throws Exception , StorageException
     */
    public UserEntity saveMedico (CreateMedicoDto newUser, MultipartFile avatar) throws Exception{

        if(newUser.getPassword().contentEquals(newUser.getPassword2())){

            String uri = null;

            if(!avatar.isEmpty()){
                String filename = storageService.store(avatar);
                String ext = StringUtils.getFilenameExtension(filename);

                BufferedImage originalImage = ImageIO.read(avatar.getInputStream());
                BufferedImage resized = imageScaler.simpleResizeImage(originalImage,128);
                OutputStream out = Files.newOutputStream(storageService.load(filename));
                ImageIO.write(resized,ext,out);

                uri = ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/download/")
                        .path(filename)
                        .toUriString();
            }else{
                throw new StorageException("No se ha podido guardar la imagen");
            }

            Especialidad especialidad = especialidadRepository.findById(newUser.getEspecialidad()).orElseThrow(() -> new RuntimeException());

            Medico medico = Medico.builder()
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .nombre(newUser.getNombre())
                    .apellidos(newUser.getApellidos())
                    .fechaNacimiento(newUser.getFechaNacimiento())
                    .avatar(uri)
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

    /**
     * Este método genera un nuevo paciente
     * @param newUser
     * @param userEntity
     * @param avatar
     * @return un objeto del tipo UserEntity
     * @throws Exception, StorageException
     */
    public UserEntity savePaciente (CreatePacienteDto newUser, UUID id, UserEntity userEntity, MultipartFile avatar) throws Exception{


        if(newUser.getPassword().contentEquals(newUser.getPassword2())){

            String uri = null;

            if(!avatar.isEmpty()){
                String filename = storageService.store(avatar);
                String ext = StringUtils.getFilenameExtension(filename);

                BufferedImage originalImage = ImageIO.read(avatar.getInputStream());
                BufferedImage resized = imageScaler.simpleResizeImage(originalImage,128);
                OutputStream out = Files.newOutputStream(storageService.load(filename));
                ImageIO.write(resized,ext,out);

                uri = ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/download/")
                        .path(filename)
                        .toUriString();
            }else{
                throw new StorageException("No se ha podido guardar la imagen");
            }
            Medico medico = medicoRepository.findById(id).orElseThrow(()-> new NotFoundException("No se ha encontrado el médico"));
            Paciente paciente = Paciente.builder()
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .nombre(newUser.getNombre())
                    .apellidos(newUser.getApellidos())
                    .avatar(uri)
                    .fechaNacimiento(newUser.getFechaNacimiento())
                    .email(newUser.getEmail())
                    .telefono(newUser.getTelefono())
                    .dni(newUser.getDni())
                    .direccion(newUser.getDireccion())
                    .rol(UserRole.PACIENTE)
                    .observaciones(newUser.getObservaciones())
                    .build();
            paciente.addMedico(medico);

            return save(paciente);
        }else{
            return null;
        }
    }
}
