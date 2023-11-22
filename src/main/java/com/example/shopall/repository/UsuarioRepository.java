package com.example.shopall.repository;

import com.example.shopall.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query("Select u from Usuario u where u.usuario = ?1")
    public Usuario login(String usuario);
}