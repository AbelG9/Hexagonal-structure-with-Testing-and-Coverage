package com.codigo.semana7.application.usercase;

import com.codigo.semana7.domain.model.Persona;
import com.codigo.semana7.domain.ports.in.PersonaIn;
import com.codigo.semana7.domain.ports.out.PersonaOut;

import java.util.Optional;

public class PersonaServiceImpl implements PersonaIn {
    private final PersonaOut personaOut;

    public PersonaServiceImpl(PersonaOut personaOut) {
        this.personaOut = personaOut;
    }

    @Override
    public Persona crearPersona(Persona persona) {
        return personaOut.createPersona(persona);
    }

    @Override
    public Optional<Persona> getPersona(Long id) {
        return personaOut.getPersona(id);
    }

    @Override
    public Optional<Persona> actualizarPersona(Long id, Persona persona) {
        return personaOut.updatePersona(id, persona);
    }

    @Override
    public boolean borrarPersona(Long id) {
        return personaOut.deletePersona(id);
    }
}
