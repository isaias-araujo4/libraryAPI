package br.com.araujo.libraryapi.autor.repository;

import br.com.araujo.libraryapi.autor.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    //query method

    // select * from autor where nome = ?
    List<Autor>findByNomeContainingIgnoreCase(String nome);

    // select * from autor where nacionalidade = ?
    List<Autor>findByNacionalidade(String nacionalidade);

    List<Autor>findByNome(String nome);

    List<Autor>findByNomeAndNacionalidade(String nome, String nacionalidade);

    Optional<Autor> findByNomeAndDataNascimentoAndNacionalidade(String nome, LocalDate dataNascimento, String nacionalidade);
}
