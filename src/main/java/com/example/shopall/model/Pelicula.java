package com.example.shopall.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "peliculas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
public class Pelicula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "director")
    private String director;

    @Column(name = "anio_estreno")
    private int anioEstreno;

    @ManyToMany
    @JoinTable(name = "pelicula_actor",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actores;

}
