package com.example.shopall;

import com.example.shopall.controller.PerfilController;
import com.example.shopall.model.Perfil;
import com.example.shopall.repository.PerfilRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PerfilControllerTest {

    @InjectMocks
    private PerfilController perfilController;

    @Mock
    private PerfilRepository perfilRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllPerfiles() {
        // Arrange
        Perfil perfil1 = new Perfil(1, "Perfil1", 1, 0, 1);
        Perfil perfil2 = new Perfil(2, "Perfil2", 0, 1, 1);
        List<Perfil> perfiles = Arrays.asList(perfil1, perfil2);

        when(perfilRepository.findAll()).thenReturn(perfiles);

        // Act
        List<Perfil> result = perfilController.getAllPerfiles();

        // Assert
        assertEquals(2, result.size());
        assertEquals(perfil1, result.get(0));
        assertEquals(perfil2, result.get(1));
    }

    @Test
    void getPerfilById() {
        // Arrange
        int perfilId = 1;
        Perfil perfil = new Perfil(perfilId, "Perfil1", 1, 0, 1);

        when(perfilRepository.findById(perfilId)).thenReturn(Optional.of(perfil));

        // Act
        Perfil result = perfilController.getPerfilById(perfilId);

        // Assert
        assertEquals(perfil, result);
    }

    @Test
    void createPerfil() {
        // Arrange
        Perfil perfil = new Perfil(1, "Nuevo Perfil", 1, 1, 2);

        when(perfilRepository.save(perfil)).thenReturn(perfil);

        // Act
        Perfil result = perfilController.createPerfil(perfil);

        // Assert
        assertEquals(perfil, result);
    }

    @Test
    void deletePerfil() {
        // Arrange
        int perfilId = 1;

        // Act
        perfilController.deletePerfil(perfilId);

        // Assert
        verify(perfilRepository, times(1)).deleteById(perfilId);
    }
}
