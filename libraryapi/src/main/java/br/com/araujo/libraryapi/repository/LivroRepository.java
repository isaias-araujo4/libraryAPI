package br.com.araujo.libraryapi.repository;

import br.com.araujo.libraryapi.model.Autor;
import br.com.araujo.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    //query method
    List<Livro>findByAutor(Autor autor);
}
