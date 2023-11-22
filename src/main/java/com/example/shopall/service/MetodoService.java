package com.example.shopall.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopall.model.Metodo;
import com.example.shopall.repository.MetodoRepository;

@Service
public class MetodoService {
    @Autowired
    MetodoRepository metodoRepository;

    public List<Metodo> getAll(){
        List<Metodo> metodos = new ArrayList<>();
        for(Metodo metodo : metodoRepository.findAll()){
            metodos.add(metodo);
        }
        return metodos;
    }

    public Metodo getByID(int id){
        Metodo metodo = metodoRepository.getById(id);
        return metodo;
    }

    public void add(Metodo metodo){
        metodoRepository.save(metodo);
    }

    public void update(int id, Metodo updatedMetodo) {
        if (metodoRepository.existsById(id)) {
            Metodo existingMetodo = metodoRepository.getById(id);
            existingMetodo.setDescripcion(updatedMetodo.getDescripcion());
            existingMetodo.setUrl(updatedMetodo.getUrl());
            existingMetodo.setMetodo(updatedMetodo.getMetodo());

            metodoRepository.save(existingMetodo);
        } else {
            throw new IllegalArgumentException("No se puede actualizar. Metodo con ID " + id + " no encontrado.");
        }
    }

    public void delete(int id) {
        if (metodoRepository.existsById(id)) {
            metodoRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No se puede eliminar. Metodo con ID " + id + " no encontrado.");
        }
    }
}
