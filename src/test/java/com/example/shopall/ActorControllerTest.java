package com.example.shopall;

import com.example.shopall.model.Actor;
import com.example.shopall.service.ActorService;
import com.example.shopall.repository.ActorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ActorControllerTest {

    @Autowired
    private ActorService actorService;

    @MockBean
    private ActorRepository actorRepository;

    /*
    @Test
    void getActorById(){      
        Actor actor2  = actorService.getByID(1);

        //System.out.println("id: "+actor.getId()+" nombre: "+actor.getNombre());
        //System.out.println("id: "+actor2.getId()+" nombre: "+actor2.getNombre());
        Assertions.assertTrue(actor2.getNombre().equals("prueba"));
        System.out.println("Correcto");
    } */

    @Test
    void getAllActors() {
        // Arrange
        Actor actor1 = new Actor(1, "Actor1");
        Actor actor2 = new Actor(2, "Actor2");
        List<Actor> actors = Arrays.asList(actor1, actor2);

        when(actorRepository.findAll()).thenReturn(actors);

        // Act
        List<Actor> result = actorService.getAll();

        // Assert
        assertEquals(2, result.size());
        assertEquals(actor1, result.get(0));
        assertEquals(actor2, result.get(1));
    }

    @Test
    void getActorById() {
        // Arrange
        int actorId = 1;
        Actor actor = new Actor(actorId, "Actor1");

        when(actorRepository.getById(actorId)).thenReturn(actor);

        // Act
        Actor result = actorService.getByID(actorId);

        // Assert
        assertEquals(actor, result);
    }

    @Test
    void createActor() {
        // Arrange
        Actor actor = new Actor(1, "Nuevo Actor");

        // Act
        actorService.add(actor);

        // Assert
        verify(actorRepository, times(1)).save(actor);
    }

    @Test
    void deleteActor() {
        // Arrange
        int actorId = 1;

        // Simula que el actor existe
        when(actorRepository.existsById(actorId)).thenReturn(true);

        // Act
        actorService.delete(actorId);

        // Assert
        verify(actorRepository, times(1)).deleteById(actorId);
    }
}
