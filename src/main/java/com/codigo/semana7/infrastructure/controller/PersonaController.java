package com.codigo.semana7.infrastructure.controller;

import com.codigo.semana7.application.service.PersonaService;
import com.codigo.semana7.domain.model.Persona;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/personas")
public class PersonaController {
    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping()
    public ResponseEntity<Persona> create(@RequestBody Persona persona) {
        Persona ceratePerson = personaService.crearPersona(persona);
        return new ResponseEntity<>(ceratePerson, HttpStatus.CREATED);
    }

    @GetMapping("/{personaId}")
    public ResponseEntity<Persona> getPersona(@PathVariable Long personaId) {
       return personaService.getPersona(personaId)
               .map(persona -> new ResponseEntity<>(persona, HttpStatus.OK))
               .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{personaId}")
    public ResponseEntity<Persona> getPersona(@PathVariable Long personaId, @RequestBody Persona persona) {
        return personaService.actualizarPersona(personaId, persona)
                .map(per -> new ResponseEntity<>(per, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{personaId}")
    public ResponseEntity<Persona> deletePersonaById(@PathVariable Long personaId) {
        personaService.borrarPersona(personaId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
