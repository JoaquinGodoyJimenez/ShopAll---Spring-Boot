package com.example.shopall;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.example.shopall.model.Actor;
import com.example.shopall.repository.ActorRepository;
import com.example.shopall.service.ActorService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ActorRepositoryTest {

    @InjectMocks
    private ActorService actorService;

    @Mock
    private ActorRepository actorRepository;

    @Test
    public void testFindById() {
        // Arrange
        int actorId = 1;
        Actor actor = new Actor(actorId, "ActorName");
        when(actorRepository.findById(actorId)).thenReturn(Optional.of(actor));

        // Act
        Optional<Actor> result = actorRepository.findById(actorId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(actorId, result.get().getId());
        assertEquals("ActorName", result.get().getNombre());
    }

    @Test
    public void testFindById_NotFound() {
        // Arrange
        int actorId = 1;
        when(actorRepository.findById(actorId)).thenReturn(Optional.empty());

        // Act
        Optional<Actor> result = actorRepository.findById(actorId);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    public void testSave() {
        // Arrange
        Actor actor = new Actor(1, "ActorName");
        when(actorRepository.save(actor)).thenReturn(actor);

        // Act
        Actor result = actorRepository.save(actor);

        // Assert
        assertEquals(actor.getId(), result.getId());
        assertEquals(actor.getNombre(), result.getNombre());
    }

    @Test
    public void testDeleteById() {
        // Arrange
        int actorId = 1;

        // Act
        actorRepository.deleteById(actorId);

        // Assert
        verify(actorRepository, times(1)).deleteById(actorId);
    }
}
