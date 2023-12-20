package com.codigo.semana7.domain.ports.in;

import com.codigo.semana7.domain.model.Persona;

import java.util.Optional;

public interface PersonaIn {
    Persona crearPersona(Persona persona);
    Optional<Persona> getPersona(Long id);
    Optional<Persona> actualizarPersona(Long id, Persona persona);
    boolean borrarPersona(Long id);
}
