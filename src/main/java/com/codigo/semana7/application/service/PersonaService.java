package com.codigo.semana7.application.service;

import com.codigo.semana7.domain.model.Persona;
import com.codigo.semana7.domain.ports.in.PersonaIn;

import java.util.Optional;

public class PersonaService implements PersonaIn {
    private final PersonaIn personaIn;

    public PersonaService(PersonaIn personaIn) {
        this.personaIn = personaIn;
    }

    @Override
    public Persona crearPersona(Persona persona) {
        return personaIn.crearPersona(persona);
    }

    @Override
    public Optional<Persona> getPersona(Long id) {
        return personaIn.getPersona(id);
    }

    @Override
    public Optional<Persona> actualizarPersona(Long id, Persona persona) {
        return personaIn.actualizarPersona(id, persona);
    }

    @Override
    public boolean borrarPersona(Long id) {
        return personaIn.borrarPersona(id);
    }
}
