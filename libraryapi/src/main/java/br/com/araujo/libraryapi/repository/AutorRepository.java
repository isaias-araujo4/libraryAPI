package br.com.araujo.libraryapi.repository;

import br.com.araujo.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    //query method

    // select * from autor where nome = ?
    List<Autor>findByNomeContainingIgnoreCase(String nome);

    // select * from autor where nacionalidade = ?
    List<Autor>findByNacionalidade(String nome);
}
