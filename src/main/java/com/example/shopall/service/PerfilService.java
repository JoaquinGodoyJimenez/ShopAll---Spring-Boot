package com.example.shopall.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopall.model.Perfil;
import com.example.shopall.repository.PerfilRepository;

@Service
public class PerfilService {
    @Autowired
    PerfilRepository perfilRepository;

    public List<Perfil> getAll(){
        List<Perfil> perfiles = new ArrayList<>();
        for(Perfil perfil : perfilRepository.findAll()){
            perfiles.add(perfil);
        }
        return perfiles;
    }

    public Perfil getByID(int id){
        Perfil perfil = perfilRepository.getById(id);
        return perfil;
    }

    public void add(Perfil perfil){
        perfilRepository.save(perfil);
    }

    public void update(int id, Perfil updatedPerfil) {
        if (perfilRepository.existsById(id)) {
            Perfil existingPerfil = perfilRepository.getById(id);
            existingPerfil.setDescripcion(updatedPerfil.getDescripcion());
            existingPerfil.setConsulta(updatedPerfil.getConsulta());
            existingPerfil.setEscritura(updatedPerfil.getEscritura());
            existingPerfil.setTotal(updatedPerfil.getTotal());
            perfilRepository.save(existingPerfil);
        } else {
            throw new IllegalArgumentException("No se puede actualizar. Perfil con ID " + id + " no encontrado.");
        }
    }

    public void delete(int id) {
        if (perfilRepository.existsById(id)) {
            perfilRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No se puede eliminar. Perfil con ID " + id + " no encontrado.");
        }
    }
}
