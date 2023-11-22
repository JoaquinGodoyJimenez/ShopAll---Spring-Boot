package com.example.shopall.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopall.model.Renta;
import com.example.shopall.repository.RentaRepository;

@Service
public class RentaService {
    @Autowired
    RentaRepository RentaRepository;

    public List<Renta> getAll(){
        List<Renta> Rentas = new ArrayList<>();
        for(Renta Renta : RentaRepository.findAll()){
            Rentas.add(Renta);
        }
        return Rentas;
    }

    public Renta getByID(int id){
        Renta Renta = RentaRepository.getById(id);
        return Renta;
    }

    public void add(Renta Renta){
        RentaRepository.save(Renta);
    }

    public void update(int id, Renta updatedRenta) {
        if (RentaRepository.existsById(id)) {
            Renta existingRenta = RentaRepository.getById(id);
            existingRenta.setFechaRenta(updatedRenta.getFechaRenta());
            existingRenta.setFechaDevolucion(updatedRenta.getFechaDevolucion());
            RentaRepository.save(existingRenta);
        } else {
            throw new IllegalArgumentException("No se puede actualizar. Renta con ID " + id + " no encontrado.");
        }
    }

    public void delete(int id) {
        if (RentaRepository.existsById(id)) {
            RentaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No se puede eliminar. Renta con ID " + id + " no encontrado.");
        }
    }
}
