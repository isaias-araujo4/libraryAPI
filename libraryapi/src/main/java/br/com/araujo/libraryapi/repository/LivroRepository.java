package br.com.araujo.libraryapi.repository;

import br.com.araujo.libraryapi.model.Autor;
import br.com.araujo.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    //query method
    //select * from livro where id_autor = id
    List<Livro>findByAutor(Autor autor);

    // select * from livros where titulo = ?
    List<Livro>findByTituloContainingIgnoreCase(String titulo);

    // select * from livros where preco = ?
    List<Livro>findByPreco(BigDecimal preco);

    // select * from livros where data_publicacao >= ? and data_publicacao <= ?
    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);
}
