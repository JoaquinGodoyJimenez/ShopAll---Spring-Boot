package com.example.shopall.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopall.model.Actor;
import com.example.shopall.repository.ActorRepository;


@Service
public class ActorService {
    @Autowired
    ActorRepository actorRepository;

    public List<Actor> getAll(){
        List<Actor> actores = new ArrayList<>();
        for(Actor actor : actorRepository.findAll()){
            actores.add(actor);
        }
        return actores;
    }

    public Actor getByID(int id){
        Actor actor = actorRepository.getById(id);
        return actor;
    }

    public void add(Actor actor){
        actorRepository.save(actor);
    }

    public void update(int id, Actor updatedActor) {
        if (actorRepository.existsById(id)) {
            Actor existingActor = actorRepository.getById(id);
            existingActor.setNombre(updatedActor.getNombre());

            // Guardar el actor actualizado en la base de datos
            actorRepository.save(existingActor);
        } else {
            // Manejar el caso en el que el actor no existe
            throw new IllegalArgumentException("No se puede actualizar. Actor con ID " + id + " no encontrado.");
        }
    }

    public void delete(int id) {
        // Verificar si el actor con el ID proporcionado existe
        if (actorRepository.existsById(id)) {
            // Eliminar el actor de la base de datos
            actorRepository.deleteById(id);
        } else {
            // Manejar el caso en el que el actor no existe
            throw new IllegalArgumentException("No se puede eliminar. Actor con ID " + id + " no encontrado.");
        }
    }
}
