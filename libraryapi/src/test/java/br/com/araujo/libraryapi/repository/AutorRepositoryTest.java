package br.com.araujo.libraryapi.repository;

import br.com.araujo.libraryapi.autor.model.Autor;
import br.com.araujo.libraryapi.autor.repository.AutorRepository;
import br.com.araujo.libraryapi.livro.model.Livro;
import br.com.araujo.libraryapi.livro.repository.LivroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    // criar um autor
    @Test
    public void  salvarAutorTest(){

        Autor autor = new Autor(); // instancioando o objeto

       var nome = "token"; // digite o nome do autor
       var nacionalidade = "holandes"; // digite a nacionalidade do autor
       var dataNascimento = LocalDate.of(1990, 3, 24); // digite a data de publicação ex: 2004, 4, 30

        autor.setNome(nome);
        autor.setNacionalidade(nacionalidade);
        autor.setDataNascimento(dataNascimento);

        var autorSalvo = autorRepository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    // atualizar um autor
    @Test
    public void atualizarAutorTest(){

        // coloque o id do autor
        Long id = 1L; // o L logo após o numero é para o java reconhecer que é um Long

        Optional<Autor> possivelAutor = autorRepository.findById(id);

        // validando se o autor existe
        if (possivelAutor.isPresent()){
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do autor");
            System.out.println(autorEncontrado);

            // tire o comentario apenas do que quiser atualizar
//            var nome = "nome do autor"; // digite o nome do autor
//            var nacionalidade = "nacionalidade do autor"; // digite a nacionalidade do autor
//            var dataNascimento = LocalDate.of() // digite a data de publicação ex: 2004, 4, 30
//
//            autor.setNome(nome);
//            autor.setNacionalidade(nacionalidade);
//            autor.setDataNascimento(dataNascimento);

            autorRepository.save(autorEncontrado);
        }
    }

    // listar todos os autores
    @Test
    public void listarAutorTest(){
        List<Autor> lista = autorRepository.findAll();
        lista.forEach(System.out::println);
    }

//    // como contar todos os autores
//    @Test
//    public void  contagemTest(){
//        System.out.println("Contagem de autores: " + autorRepository.count());
//    }

    // deletando um autor
    @Test
    public  void deletarAutorPorId(){

        // coloque o id do autor
        Long id = 1L; // o L logo após o numero é para o java reconhecer que é um Long

        autorRepository.deleteById(id);
    }

    // listar livros por autor
    @Test
    public void listarLivrosPorAutor(){
        // coloque o id do autor
        Long id = 2L; // o L logo após o numero é para o java reconhecer que é um Long
        var  autor = autorRepository.findById(id).get();

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);
    }

    //pesquisar autor por nome
    @Test
    public void pesquisarAutorNome(){
        List<Autor> lista = autorRepository.findByNomeContainingIgnoreCase("nome do autor"); // digite o nome do autor
        lista.forEach(System.out::println);
    }

    //pesquisar autor por nacionalidade
    @Test
    public void pesquisarAutorNacionalidade(){
        List<Autor> lista = autorRepository.findByNacionalidade("nacionalidade do autor"); // digite a nacionalidade do autor
        lista.forEach(System.out::println);
    }

}
