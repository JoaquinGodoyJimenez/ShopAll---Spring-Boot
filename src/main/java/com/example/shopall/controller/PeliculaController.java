package com.example.shopall.controller;

import com.example.shopall.model.Pelicula;
import com.example.shopall.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @GetMapping
    public List<Pelicula> getAllPeliculas() {
        return peliculaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pelicula getPeliculaById(@PathVariable int id) {
        return peliculaRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Pelicula createPelicula(@RequestBody Pelicula Pelicula) {
        return peliculaRepository.save(Pelicula);
    }

    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable int id) {
        peliculaRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> updatePelicula(@PathVariable int id, @RequestBody Pelicula updatedPelicula) {
        Optional<Pelicula> optionalPelicula = peliculaRepository.findById(id);

        if (optionalPelicula.isPresent()) {
            Pelicula existingPelicula = optionalPelicula.get();
            existingPelicula.setDirector(updatedPelicula.getDirector());
            existingPelicula.setAnioEstreno(updatedPelicula.getAnioEstreno());

            Pelicula savedPelicula = peliculaRepository.save(existingPelicula);
            return new ResponseEntity<>(savedPelicula, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
