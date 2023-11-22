package com.example.shopall;

import com.example.shopall.controller.ModuloController;
import com.example.shopall.model.Modulo;
import com.example.shopall.repository.ModuloRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ModuloControllerTest {

    @InjectMocks
    private ModuloController moduloController;

    @Mock
    private ModuloRepository moduloRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllModulos() {
        // Arrange
        Modulo modulo1 = new Modulo(1, "Modulo1", "URL1");
        Modulo modulo2 = new Modulo(2, "Modulo2", "URL2");
        List<Modulo> modulos = Arrays.asList(modulo1, modulo2);

        when(moduloRepository.findAll()).thenReturn(modulos);

        // Act
        List<Modulo> result = moduloController.getAllModulos();

        // Assert
        assertEquals(2, result.size());
        assertEquals(modulo1, result.get(0));
        assertEquals(modulo2, result.get(1));
    }

    @Test
    void getModuloById() {
        // Arrange
        int moduloId = 1;
        Modulo modulo = new Modulo(moduloId, "Modulo1", "URL1");

        when(moduloRepository.findById(moduloId)).thenReturn(Optional.of(modulo));

        // Act
        Modulo result = moduloController.getModuloById(moduloId);

        // Assert
        assertEquals(modulo, result);
    }

    @Test
    void createModulo() {
        // Arrange
        Modulo modulo = new Modulo(1, "Nuevo Modulo", "Nueva URL");

        when(moduloRepository.save(modulo)).thenReturn(modulo);

        // Act
        Modulo result = moduloController.createModulo(modulo);

        // Assert
        assertEquals(modulo, result);
    }

    @Test
    void deleteModulo() {
        // Arrange
        int moduloId = 1;

        // Act
        moduloController.deleteModulo(moduloId);

        // Assert
        verify(moduloRepository, times(1)).deleteById(moduloId);
    }

    @Test
    void updateModulo() {
        // Arrange
        int moduloId = 1;
        Modulo existingModulo = new Modulo(moduloId, "Modulo1", "URL1");
        Modulo updatedModulo = new Modulo(moduloId, "Updated Modulo", "Updated URL");

        when(moduloRepository.findById(moduloId)).thenReturn(Optional.of(existingModulo));
        when(moduloRepository.save(existingModulo)).thenReturn(updatedModulo);

        // Act
        ResponseEntity<Modulo> result = moduloController.updateModulo(moduloId, updatedModulo);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(updatedModulo, result.getBody());
    }
}
