package com.example.shopall.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "password")
    private String password;
    @JoinColumn(name = "idpersona")
    @OneToOne(fetch = FetchType.EAGER)
    private Persona persona;
    
    @JoinColumn(name = "idperfil")
    @OneToOne(fetch = FetchType.EAGER)
    private Perfil perfil;
    
    @JoinColumn(name = "idperfil",referencedColumnName = "idperfil")
    @OneToMany(fetch = FetchType.EAGER)
    private List<PerfilMetodo> metodos;
}