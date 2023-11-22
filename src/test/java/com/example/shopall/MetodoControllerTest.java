package com.example.shopall;

import com.example.shopall.controller.MetodoController;
import com.example.shopall.model.Metodo;
import com.example.shopall.repository.MetodoRepository;
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

class MetodoControllerTest {

    @InjectMocks
    private MetodoController metodoController;

    @Mock
    private MetodoRepository metodoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllMetodos() {
        // Arrange
        Metodo metodo1 = new Metodo(1, null, "Desc1", "URL1", "GET");
        Metodo metodo2 = new Metodo(2, null, "Desc2", "URL2", "POST");
        List<Metodo> metodos = Arrays.asList(metodo1, metodo2);

        when(metodoRepository.findAll()).thenReturn(metodos);

        // Act
        List<Metodo> result = metodoController.getAllMetodos();

        // Assert
        assertEquals(2, result.size());
        assertEquals(metodo1, result.get(0));
        assertEquals(metodo2, result.get(1));
    }

    @Test
    void getMetodoById() {
        // Arrange
        int metodoId = 1;
        Metodo metodo = new Metodo(metodoId, null, "Desc", "URL", "PUT");

        when(metodoRepository.findById(metodoId)).thenReturn(Optional.of(metodo));

        // Act
        Metodo result = metodoController.getMetodoById(metodoId);

        // Assert
        assertEquals(metodo, result);
    }

    @Test
    void createMetodo() {
        // Arrange
        Metodo metodo = new Metodo(1, null, "Desc", "URL", "DELETE");

        when(metodoRepository.save(metodo)).thenReturn(metodo);

        // Act
        Metodo result = metodoController.createMetodo(metodo);

        // Assert
        assertEquals(metodo, result);
    }

    @Test
    void deleteMetodo() {
        // Arrange
        int metodoId = 1;

        // Act
        metodoController.deleteMetodo(metodoId);

        // Assert
        verify(metodoRepository, times(1)).deleteById(metodoId);
    }

    @Test
    void updateMetodo() {
        // Arrange
        int metodoId = 1;
        Metodo existingMetodo = new Metodo(metodoId, null, "Desc", "URL", "PATCH");
        Metodo updatedMetodo = new Metodo(metodoId, null, "Updated Desc", "Updated URL", "PUT");

        when(metodoRepository.findById(metodoId)).thenReturn(Optional.of(existingMetodo));
        when(metodoRepository.save(existingMetodo)).thenReturn(updatedMetodo);

        // Act
        ResponseEntity<Metodo> result = metodoController.updateMetodo(metodoId, updatedMetodo);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(updatedMetodo, result.getBody());
    }
}
