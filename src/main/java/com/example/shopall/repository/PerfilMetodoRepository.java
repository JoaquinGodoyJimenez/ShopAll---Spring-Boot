package com.example.shopall.repository;

import com.example.shopall.model.PerfilMetodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilMetodoRepository extends JpaRepository<PerfilMetodo, Integer> {
}