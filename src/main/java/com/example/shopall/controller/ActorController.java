package com.example.shopall.controller;

import com.example.shopall.model.Actor;
import com.example.shopall.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/actores")
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    @GetMapping
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    @GetMapping("/getbyid")
    public Actor getActorById(@RequestParam int id) {
        return actorRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Actor createActor(@RequestBody Actor actor) {
        return actorRepository.save(actor);
    }

    @DeleteMapping("/{id}")
    public void deleteActor(@PathVariable int id) {
        actorRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable int id, @RequestBody Actor updatedActor) {
        Optional<Actor> optionalActor = actorRepository.findById(id);

        if (optionalActor.isPresent()) {
            Actor existingActor = optionalActor.get();
            existingActor.setNombre(updatedActor.getNombre()); 

            Actor savedActor = actorRepository.save(existingActor);
            return new ResponseEntity<>(savedActor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
