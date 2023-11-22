package com.example.shopall.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopall.model.Persona;
import com.example.shopall.repository.PersonaRepository;

@Service
public class PersonaService {
    @Autowired
    PersonaRepository PersonaRepository;

    public List<Persona> getAll(){
        List<Persona> Personas = new ArrayList<>();
        for(Persona Persona : PersonaRepository.findAll()){
            Personas.add(Persona);
        }
        return Personas;
    }

    public Persona getByID(int id){
        Persona Persona = PersonaRepository.getById(id);
        return Persona;
    }

    public void add(Persona Persona){
        PersonaRepository.save(Persona);
    }

    public void update(int id, Persona updatedPersona) {
        if (PersonaRepository.existsById(id)) {
            Persona existingPersona = PersonaRepository.getById(id);
            existingPersona.setNombre(updatedPersona.getNombre());
            existingPersona.setApellidos(updatedPersona.getApellidos());
            existingPersona.setEdad(updatedPersona.getEdad());
            PersonaRepository.save(existingPersona);
        } else {
            throw new IllegalArgumentException("No se puede actualizar. Persona con ID " + id + " no encontrado.");
        }
    }

    public void delete(int id) {
        if (PersonaRepository.existsById(id)) {
            PersonaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No se puede eliminar. Persona con ID " + id + " no encontrado.");
        }
    }
}
