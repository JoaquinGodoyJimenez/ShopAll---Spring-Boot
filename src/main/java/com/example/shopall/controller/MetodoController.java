package com.example.shopall.controller;

import com.example.shopall.model.Actor;
import com.example.shopall.model.Metodo;
import com.example.shopall.repository.MetodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/metodos")
public class MetodoController {

    @Autowired
    private MetodoRepository metodoRepository;

    @GetMapping
    public List<Metodo> getAllMetodos() {
        return metodoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Metodo getMetodoById(@PathVariable int id) {
        return metodoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Metodo createMetodo(@RequestBody Metodo metodo) {
        return metodoRepository.save(metodo);
    }

    @DeleteMapping("/{id}")
    public void deleteMetodo(@PathVariable int id) {
        metodoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Metodo> updateMetodo(@PathVariable int id, @RequestBody Metodo updatedMetodo) {
        Optional<Metodo> optionalMetodo = metodoRepository.findById(id);

        if (optionalMetodo.isPresent()) {
            Metodo existingMetodo = optionalMetodo.get();
            existingMetodo.setDescripcion(updatedMetodo.getDescripcion());
            existingMetodo.setUrl(updatedMetodo.getUrl());
            existingMetodo.setMetodo(updatedMetodo.getMetodo());

            Metodo savedMetodo = metodoRepository.save(existingMetodo);
            return new ResponseEntity<>(savedMetodo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
