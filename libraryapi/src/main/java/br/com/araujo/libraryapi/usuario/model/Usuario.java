package br.com.araujo.libraryapi.usuario.model;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity
@Table
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false)
    private  String nome;

    @Column(unique = true, nullable = false)
    private  String email;

    @Column(nullable = false)
    private String senha;

    @Type(ListArrayType.class)
    @Column(nullable = false, columnDefinition = "varchar[]")
    private List<String> roles;
}
