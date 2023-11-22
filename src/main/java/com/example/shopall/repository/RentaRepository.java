package com.example.shopall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopall.model.Renta;

@Repository
public interface RentaRepository extends JpaRepository<Renta, Integer> {

    
}
