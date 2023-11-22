package com.example.shopall.controller;

import com.example.shopall.model.Perfil;
import com.example.shopall.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfiles")
public class PerfilController {

    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping
    public List<Perfil> getAllPerfiles() {
        return perfilRepository.findAll();
    }

    @GetMapping("/{id}")
    public Perfil getPerfilById(@PathVariable int id) {
        return perfilRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Perfil createPerfil(@RequestBody Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    @DeleteMapping("/{id}")
    public void deletePerfil(@PathVariable int id) {
        perfilRepository.deleteById(id);
    }

}
