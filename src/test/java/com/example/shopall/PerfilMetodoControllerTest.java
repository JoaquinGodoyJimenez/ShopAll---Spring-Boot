package com.example.shopall;

import com.example.shopall.controller.PerfilMetodoController;
import com.example.shopall.model.Metodo;
import com.example.shopall.model.PerfilMetodo;
import com.example.shopall.repository.PerfilMetodoRepository;
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

class PerfilMetodoControllerTest {

    @InjectMocks
    private PerfilMetodoController perfilMetodoController;

    @Mock
    private PerfilMetodoRepository perfilMetodoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllPerfilMetodos() {
        // Arrange
        PerfilMetodo perfilMetodo1 = new PerfilMetodo(1, new Metodo(1, null, "Desc1", "URL1", "GET")); 
        PerfilMetodo perfilMetodo2 = new PerfilMetodo(2, new Metodo(2, null, "Desc2", "URL2", "POST")); 
        List<PerfilMetodo> perfilMetodos = Arrays.asList(perfilMetodo1, perfilMetodo2);

        when(perfilMetodoRepository.findAll()).thenReturn(perfilMetodos);

        // Act
        List<PerfilMetodo> result = perfilMetodoController.getAllPerfilMetodos();

        // Assert
        assertEquals(2, result.size());
        assertEquals(perfilMetodo1, result.get(0));
        assertEquals(perfilMetodo2, result.get(1));
    }

    @Test
    void getPerfilMetodoById() {
        // Arrange
        int perfilMetodoId = 1;
        PerfilMetodo perfilMetodo = new PerfilMetodo(perfilMetodoId, new Metodo());

        when(perfilMetodoRepository.findById(perfilMetodoId)).thenReturn(Optional.of(perfilMetodo));

        // Act
        PerfilMetodo result = perfilMetodoController.getPerfilMetodoById(perfilMetodoId);

        // Assert
        assertEquals(perfilMetodo, result);
    }

    @Test
    void createPerfilMetodo() {
        // Arrange
        PerfilMetodo perfilMetodo = new PerfilMetodo(1, new Metodo());

        when(perfilMetodoRepository.save(perfilMetodo)).thenReturn(perfilMetodo);

        // Act
        PerfilMetodo result = perfilMetodoController.createActor(perfilMetodo);

        // Assert
        assertEquals(perfilMetodo, result);
    }

    @Test
    void deletePerfilMetodo() {
        // Arrange
        int perfilMetodoId = 1;

        // Act
        perfilMetodoController.deletePerfilMetodo(perfilMetodoId);

        // Assert
        verify(perfilMetodoRepository, times(1)).deleteById(perfilMetodoId);
    }
}
