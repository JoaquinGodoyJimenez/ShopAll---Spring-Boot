package com.example.shopall.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopall.model.Pelicula;
import com.example.shopall.repository.PeliculaRepository;

@Service
public class PeliculaService {
    @Autowired
    PeliculaRepository PeliculaRepository;

    public List<Pelicula> getAll(){
        List<Pelicula> Peliculas = new ArrayList<>();
        for(Pelicula Pelicula : PeliculaRepository.findAll()){
            Peliculas.add(Pelicula);
        }
        return Peliculas;
    }

    public Pelicula getByID(int id){
        Pelicula Pelicula = PeliculaRepository.getById(id);
        return Pelicula;
    }

    public void add(Pelicula Pelicula){
        PeliculaRepository.save(Pelicula);
    }

    public void update(int id, Pelicula updatedPelicula) {
        if (PeliculaRepository.existsById(id)) {
            Pelicula existingPelicula = PeliculaRepository.getById(id);
            existingPelicula.setTitulo(updatedPelicula.getTitulo());
            existingPelicula.setDirector(updatedPelicula.getDirector());
            existingPelicula.setAnioEstreno(updatedPelicula.getAnioEstreno());

            PeliculaRepository.save(existingPelicula);
        } else {
            throw new IllegalArgumentException("No se puede actualizar. Pelicula con ID " + id + " no encontrado.");
        }
    }

    public void delete(int id) {
        if (PeliculaRepository.existsById(id)) {
            PeliculaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No se puede eliminar. Pelicula con ID " + id + " no encontrado.");
        }
    }
}
