package com.example.shopall.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopall.model.Modulo;
import com.example.shopall.repository.ModuloRepository;

@Service
public class ModuloService {
    @Autowired
    ModuloRepository moduloRepository;

    public List<Modulo> getAll(){
        List<Modulo> modulos = new ArrayList<>();
        for(Modulo modulo : moduloRepository.findAll()){
            modulos.add(modulo);
        }
        return modulos;
    }

    public Modulo getByID(int id){
        Modulo modulo = moduloRepository.getById(id);
        return modulo;
    }

    public void add(Modulo modulo){
        moduloRepository.save(modulo);
    }

    public void update(int id, Modulo updatedModulo) {
        if (moduloRepository.existsById(id)) {
            Modulo existingModulo = moduloRepository.getById(id);
            existingModulo.setDescripcion(updatedModulo.getDescripcion());
            existingModulo.setUrl(updatedModulo.getUrl());
            moduloRepository.save(existingModulo);
        } else {
            throw new IllegalArgumentException("No se puede actualizar. Modulo con ID " + id + " no encontrado.");
        }
    }

    public void delete(int id) {
        if (moduloRepository.existsById(id)) {
            moduloRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No se puede eliminar. Modulo con ID " + id + " no encontrado.");
        }
    }
}
