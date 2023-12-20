package com.codigo.semana7.domain.ports.out;

import com.codigo.semana7.domain.model.Persona;

import java.util.Optional;

public interface PersonaOut {
    Persona createPersona(Persona persona);
    Optional<Persona> getPersona(Long id);
    Optional<Persona> updatePersona(Long id, Persona persona);
    boolean deletePersona(Long id);
}
