package com.example.shopall.repository;

import com.example.shopall.model.Metodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoRepository extends JpaRepository<Metodo, Integer> {
}