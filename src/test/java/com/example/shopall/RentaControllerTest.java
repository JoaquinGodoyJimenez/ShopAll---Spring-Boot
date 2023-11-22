package com.example.shopall;

import com.example.shopall.controller.RentaController;
import com.example.shopall.model.Renta;
import com.example.shopall.repository.RentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RentaControllerTest {

    @InjectMocks
    private RentaController rentaController;

    @Mock
    private RentaRepository rentaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllRentas() {
        // Arrange
        Renta renta1 = new Renta(1, null, null, LocalDate.now(), LocalDate.now().plusDays(7));
        Renta renta2 = new Renta(2, null, null, LocalDate.now(), LocalDate.now().plusDays(14));
        List<Renta> rentas = Arrays.asList(renta1, renta2);

        when(rentaRepository.findAll()).thenReturn(rentas);

        // Act
        List<Renta> result = rentaController.getAllRentas();

        // Assert
        assertEquals(2, result.size());
        assertEquals(renta1, result.get(0));
        assertEquals(renta2, result.get(1));
    }

    @Test
    void getRentaById() {
        // Arrange
        int rentaId = 1;
        Renta renta = new Renta(rentaId, null, null, LocalDate.now(), LocalDate.now().plusDays(7));

        when(rentaRepository.findById(rentaId)).thenReturn(Optional.of(renta));

        // Act
        Renta result = rentaController.getRentaById(rentaId);

        // Assert
        assertEquals(renta, result);
    }

    @Test
    void createRenta() {
        // Arrange
        Renta renta = new Renta(1, null, null, LocalDate.now(), LocalDate.now().plusDays(7));

        when(rentaRepository.save(renta)).thenReturn(renta);

        // Act
        Renta result = rentaController.createRenta(renta);

        // Assert
        assertEquals(renta, result);
    }

    @Test
    void deleteRenta() {
        // Arrange
        int rentaId = 1;

        // Act
        rentaController.deleteRenta(rentaId);

        // Assert
        verify(rentaRepository, times(1)).deleteById(rentaId);
    }

    @Test
    void updateRenta() {
        // Arrange
        int rentaId = 1;
        Renta existingRenta = new Renta(rentaId, null, null, LocalDate.now(), LocalDate.now().plusDays(7));
        Renta updatedRenta = new Renta(rentaId, null, null, LocalDate.now(), LocalDate.now().plusDays(14));

        when(rentaRepository.findById(rentaId)).thenReturn(Optional.of(existingRenta));
        when(rentaRepository.save(existingRenta)).thenReturn(updatedRenta);

        // Act
        ResponseEntity<Renta> result = rentaController.updateRenta(rentaId, updatedRenta);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(updatedRenta, result.getBody());
    }
}
