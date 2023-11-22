package com.example.shopall.controller;

import com.example.shopall.model.Modulo;
import com.example.shopall.repository.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/modulos")
public class ModuloController {

    @Autowired
    private ModuloRepository moduloRepository;

    @GetMapping
    public List<Modulo> getAllModulos() {
        return moduloRepository.findAll();
    }

    @GetMapping("/{id}")
    public Modulo getModuloById(@PathVariable int id) {
        return moduloRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Modulo createModulo(@RequestBody Modulo modulo) {
        return moduloRepository.save(modulo);
    }

    @DeleteMapping("/{id}")
    public void deleteModulo(@PathVariable int id) {
        moduloRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modulo> updateModulo(@PathVariable int id, @RequestBody Modulo updatedModulo) {
        Optional<Modulo> optionalModulo = moduloRepository.findById(id);

        if (optionalModulo.isPresent()) {
            Modulo existingModulo = optionalModulo.get();
            existingModulo.setDescripcion(updatedModulo.getDescripcion());
            existingModulo.setUrl(updatedModulo.getUrl());

            Modulo savedModulo = moduloRepository.save(existingModulo);
            return new ResponseEntity<>(savedModulo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
