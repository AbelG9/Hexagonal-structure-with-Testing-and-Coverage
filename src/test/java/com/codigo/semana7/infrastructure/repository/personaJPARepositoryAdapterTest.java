package com.codigo.semana7.infrastructure.repository;

import com.codigo.semana7.domain.model.Persona;
import com.codigo.semana7.infrastructure.entity.PersonaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    void getPersona() {
    }

    @Test
    void updatePersona() {
    }

    @Test
    void eliminarPersona() {
    }
}