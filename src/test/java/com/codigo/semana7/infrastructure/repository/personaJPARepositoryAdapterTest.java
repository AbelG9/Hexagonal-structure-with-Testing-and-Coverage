package com.codigo.semana7.infrastructure.repository;

import com.codigo.semana7.domain.model.Persona;
import com.codigo.semana7.domain.ports.out.PersonaOut;
import com.codigo.semana7.infrastructure.entity.PersonaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class personaJPARepositoryAdapterTest {
    @Mock
    PersonaJPARepository personaJPARepository;

    @InjectMocks
    PersonaJPARepositoryAdapter personaJPARepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        personaJPARepositoryAdapter = new PersonaJPARepositoryAdapter(personaJPARepository);
    }

    @Test
    void createPersona() {
        //preparar
        Persona persona = new Persona(1L, "Paul", "Rodriguez", new Date(2020, 1, 1), "Masculino");

        PersonaEntity personaEntity = new PersonaEntity(1L, "Paul", "Rodriguez", new Date(2020, 1, 1), "Masculino");

        //simular y ejecutar
        Mockito.when(personaJPARepository.save(Mockito.any(PersonaEntity.class))).thenReturn(personaEntity);

        Persona personaAdapter = personaJPARepositoryAdapter.createPersona(persona);

        //afirmar
        assertNotNull(personaAdapter);
        assertEquals(persona.getId(), personaAdapter.getId());
    }

    @Test
    void findById_IsEmpty() {
        Optional<PersonaEntity> personas = Optional.of(new PersonaEntity());
        Long idPersona = 1L;

        Mockito.when(personaJPARepository.findById(idPersona)).thenReturn(personas);
        Optional<Persona> personaAdapter = personaJPARepositoryAdapter.getPersona(idPersona);

        assertTrue(personaAdapter.get().getId() == null);
    }

    @Test
    void findById_ExistingId_ReturnsPersona() {
        Long idPersona = 5L;
        PersonaEntity personaEntity = new PersonaEntity(5L, "Paul", "Rodriguez", new Date(2020, 1, 1), "Masculino");

        Mockito.when(personaJPARepository.findById(idPersona)).thenReturn(Optional.of(personaEntity));
        Optional<Persona> personaAdapter = personaJPARepositoryAdapter.getPersona(idPersona);

        assertTrue(personaAdapter.isPresent());
        assertFalse(personaAdapter.isEmpty());
        assertEquals(personaEntity.getId(), idPersona);
        assertEquals(personaAdapter.get().getNombre(), personaEntity.getNombre());
        assertEquals(personaAdapter.get().getApellido(), personaEntity.getApellido());
        assertEquals(personaAdapter.get().getGenero(), personaEntity.getGenero());
        assertEquals(personaAdapter.get().getFechaNacimiento(), personaEntity.getFechaNacimiento());
    }

    @Test
    void update_ExistingIdAndValidPersona_ReturnsUpdatedPersona() {
        Persona personaEdit = new Persona(null,"Abel", "Guevara", new Date(1990, 1, 1), "Masculino");
        PersonaEntity nuevaPersona = new PersonaEntity(1L, "Abel", "Guevara", new Date(1990, 1, 1), "Masculino");
        Long idPersona = 1L;

        Mockito.when(personaJPARepository.existsById(idPersona)).thenReturn(true);
        Mockito.when(personaJPARepository.save(Mockito.any(PersonaEntity.class))).thenReturn(nuevaPersona);
        Optional<Persona> personaAdapter = personaJPARepositoryAdapter.updatePersona(idPersona, personaEdit);

        assertTrue(personaAdapter.isPresent());
        assertFalse(personaAdapter.isEmpty());
        assertEquals(personaAdapter.get().getId(), nuevaPersona.getId());
        assertEquals(personaAdapter.get().getNombre(), nuevaPersona.getNombre());
        assertEquals(personaAdapter.get().getApellido(), nuevaPersona.getApellido());
        assertEquals(personaAdapter.get().getGenero(), nuevaPersona.getGenero());
        assertEquals(personaAdapter.get().getFechaNacimiento(), nuevaPersona.getFechaNacimiento());
    }

    @Test
    void update_NonExistingId_ReturnsEmptyOptional() {
        Persona personaEdit = new Persona(null,"Abel", "Guevara", new Date(1990, 1, 1), "Masculino");
        Long idPersona = 1L;

        Mockito.when(personaJPARepository.existsById(idPersona)).thenReturn(false);
        Optional<Persona> personaAdapter = personaJPARepositoryAdapter.updatePersona(idPersona, personaEdit);

        assertFalse(personaAdapter.isPresent());
        assertTrue(personaAdapter.isEmpty());
    }

    @Test
    void deleteById_NonExistingId_ReturnsFalse() {
        Long idPersona = 1L;
        Mockito.when(personaJPARepository.existsById(idPersona)).thenReturn(false);
        boolean personaDeleted = personaJPARepositoryAdapter.deletePersona(idPersona);

        assertFalse(personaDeleted);
    }

    @Test
    void deleteById_ExistingId_ReturnsTrue() {
        Long idPersona = 1L;
        Mockito.when(personaJPARepository.existsById(idPersona)).thenReturn(true);

        boolean personaDeleted = personaJPARepositoryAdapter.deletePersona(idPersona);

        assertTrue(personaDeleted);
    }
}