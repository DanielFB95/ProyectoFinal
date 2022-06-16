package com.salesianostriana.dam.ProyectoFinal.validacion.validadores;

import com.salesianostriana.dam.ProyectoFinal.repositories.EspecialidadRepository;
import com.salesianostriana.dam.ProyectoFinal.repositories.MedicamentoRepository;
import com.salesianostriana.dam.ProyectoFinal.validacion.anotaciones.NameUnique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameUniqueValidator implements ConstraintValidator<NameUnique,String> {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    public void initialize(NameUnique constraintAnnotation) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return StringUtils.hasText(name) && !especialidadRepository.existsByNombre(name) && !medicamentoRepository.existsByNombre(name);
    }
}
