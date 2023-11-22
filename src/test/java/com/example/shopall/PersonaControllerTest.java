package com.example.shopall;

import com.example.shopall.controller.PersonaController;
import com.example.shopall.model.Persona;
import com.example.shopall.repository.PersonaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PersonaControllerTest {

    @InjectMocks
    private PersonaController personaController;

    @Mock
    private PersonaRepository personaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllPersonas() {
        // Arrange
        Persona persona1 = new Persona(1, "Nombre1", "Apellido1", 25);
        Persona persona2 = new Persona(2, "Nombre2", "Apellido2", 30);
        List<Persona> personas = Arrays.asList(persona1, persona2);

        when(personaRepository.findAll()).thenReturn(personas);

        // Act
        List<Persona> result = personaController.getAllPersonas();

        // Assert
        assertEquals(2, result.size());
        assertEquals(persona1, result.get(0));
        assertEquals(persona2, result.get(1));
    }

    @Test
    void getPersonaById() {
        // Arrange
        int personaId = 1;
        Persona persona = new Persona(personaId, "Nombre", "Apellido", 25);

        when(personaRepository.findById(personaId)).thenReturn(Optional.of(persona));

        // Act
        Persona result = personaController.getPersonaById(personaId);

        // Assert
        assertEquals(persona, result);
    }

    @Test
    void createPersona() {
        // Arrange
        Persona persona = new Persona(1, "NuevoNombre", "NuevoApellido", 30);

        when(personaRepository.save(persona)).thenReturn(persona);

        // Act
        Persona result = personaController.createPersona(persona);

        // Assert
        assertEquals(persona, result);
    }

    @Test
    void deletePersona() {
        // Arrange
        int personaId = 1;

        // Act
        personaController.deletePersona(personaId);

        // Assert
        verify(personaRepository, times(1)).deleteById(personaId);
    }
}
