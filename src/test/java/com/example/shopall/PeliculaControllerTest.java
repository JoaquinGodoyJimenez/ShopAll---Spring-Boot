package com.example.shopall;

import com.example.shopall.controller.PeliculaController;
import com.example.shopall.model.Pelicula;
import com.example.shopall.repository.PeliculaRepository;
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

class PeliculaControllerTest {

    @InjectMocks
    private PeliculaController peliculaController;

    @Mock
    private PeliculaRepository peliculaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllPeliculas() {
        // Arrange
        Pelicula pelicula1 = new Pelicula(1, "Pelicula1", "Director1", 2022, null);
        Pelicula pelicula2 = new Pelicula(2, "Pelicula2", "Director2", 2023, null);
        List<Pelicula> peliculas = Arrays.asList(pelicula1, pelicula2);

        when(peliculaRepository.findAll()).thenReturn(peliculas);

        // Act
        List<Pelicula> result = peliculaController.getAllPeliculas();

        // Assert
        assertEquals(2, result.size());
        assertEquals(pelicula1, result.get(0));
        assertEquals(pelicula2, result.get(1));
    }

    @Test
    void getPeliculaById() {
        // Arrange
        int peliculaId = 1;
        Pelicula pelicula = new Pelicula(peliculaId, "Pelicula1", "Director1", 2022, null);

        when(peliculaRepository.findById(peliculaId)).thenReturn(Optional.of(pelicula));

        // Act
        Pelicula result = peliculaController.getPeliculaById(peliculaId);

        // Assert
        assertEquals(pelicula, result);
    }

    @Test
    void createPelicula() {
        // Arrange
        Pelicula pelicula = new Pelicula(1, "Nueva Pelicula", "Nuevo Director", 2023, null);

        when(peliculaRepository.save(pelicula)).thenReturn(pelicula);

        // Act
        Pelicula result = peliculaController.createPelicula(pelicula);

        // Assert
        assertEquals(pelicula, result);
    }

    @Test
    void deletePelicula() {
        // Arrange
        int peliculaId = 1;

        // Act
        peliculaController.deletePelicula(peliculaId);

        // Assert
        verify(peliculaRepository, times(1)).deleteById(peliculaId);
    }

    @Test
    void updatePelicula() {
        // Arrange
        int peliculaId = 1;
        Pelicula existingPelicula = new Pelicula(peliculaId, "Pelicula1", "Director1", 2022, null);
        Pelicula updatedPelicula = new Pelicula(peliculaId, "Updated Pelicula", "Updated Director", 2023, null);

        when(peliculaRepository.findById(peliculaId)).thenReturn(Optional.of(existingPelicula));
        when(peliculaRepository.save(existingPelicula)).thenReturn(updatedPelicula);

        // Act
        ResponseEntity<Pelicula> result = peliculaController.updatePelicula(peliculaId, updatedPelicula);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(updatedPelicula, result.getBody());
    }
}
