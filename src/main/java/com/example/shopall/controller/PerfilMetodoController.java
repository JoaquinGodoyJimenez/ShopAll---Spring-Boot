package com.example.shopall.controller;

import com.example.shopall.model.PerfilMetodo;
import com.example.shopall.repository.PerfilMetodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfilMetodo")
public class PerfilMetodoController {

    @Autowired
    private PerfilMetodoRepository perfilMetodoRepository;

    @GetMapping
    public List<PerfilMetodo> getAllPerfilMetodos() {
        return perfilMetodoRepository.findAll();
    }

    @GetMapping("/{id}")
    public PerfilMetodo getPerfilMetodoById(@PathVariable int id) {
        return perfilMetodoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public PerfilMetodo createActor(@RequestBody PerfilMetodo perfilMetodo) {
        return perfilMetodoRepository.save(perfilMetodo);
    }

    @DeleteMapping("/{id}")
    public void deletePerfilMetodo(@PathVariable int id) {
        perfilMetodoRepository.deleteById(id);
    }
}
