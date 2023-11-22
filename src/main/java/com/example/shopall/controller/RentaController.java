package com.example.shopall.controller;

import com.example.shopall.model.Renta;
import com.example.shopall.repository.RentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rentas")
public class RentaController {

    @Autowired
    private RentaRepository rentaRepository;

    @GetMapping
    public List<Renta> getAllRentas() {
        return rentaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Renta getRentaById(@PathVariable int id) {
        return rentaRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Renta createRenta(@RequestBody Renta renta) {
        return rentaRepository.save(renta);
    }

    @DeleteMapping("/{id}")
    public void deleteRenta(@PathVariable int id) {
        rentaRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Renta> updateRenta(@PathVariable int id, @RequestBody Renta updatedRenta) {
        Optional<Renta> optionalRenta = rentaRepository.findById(id);

        if (optionalRenta.isPresent()) {
            Renta existingRenta = optionalRenta.get();
            existingRenta.setFechaRenta(updatedRenta.getFechaRenta());
            existingRenta.setFechaDevolucion(updatedRenta.getFechaDevolucion());

            Renta savedRenta = rentaRepository.save(existingRenta);
            return new ResponseEntity<>(savedRenta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
