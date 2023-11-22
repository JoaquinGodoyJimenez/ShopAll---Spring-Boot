package com.example.shopall.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopall.model.PerfilMetodo;
import com.example.shopall.repository.PerfilMetodoRepository;

@Service
public class PerfilMetodoService {
     @Autowired
    PerfilMetodoRepository PerfilMetodoRepository;

    public List<PerfilMetodo> getAll(){
        List<PerfilMetodo> PerfilMetodos = new ArrayList<>();
        for(PerfilMetodo PerfilMetodo : PerfilMetodoRepository.findAll()){
            PerfilMetodos.add(PerfilMetodo);
        }
        return PerfilMetodos;
    }

    public PerfilMetodo getByID(int id){
        PerfilMetodo PerfilMetodo = PerfilMetodoRepository.getById(id);
        return PerfilMetodo;
    }

    public void add(PerfilMetodo PerfilMetodo){
        PerfilMetodoRepository.save(PerfilMetodo);
    }

    public void delete(int id) {
        if (PerfilMetodoRepository.existsById(id)) {
            PerfilMetodoRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No se puede eliminar. PerfilMetodo con ID " + id + " no encontrado.");
        }
    }
}
