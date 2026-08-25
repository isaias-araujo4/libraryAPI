package br.com.araujo.libraryapi.repository;

import br.com.araujo.libraryapi.model.Autor;
import br.com.araujo.libraryapi.model.GeneroLivro;
import br.com.araujo.libraryapi.model.Livro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class LivroRepositoryTest {


    @Autowired
    LivroRepository livroRepository;


    @Autowired
    AutorRepository autorRepository;


    // criar um livro
    @Test
    public void salvarLivroTest(){

        // instanciando um novo objeto livro
        Livro livro = new Livro();

        var preco = BigDecimal.valueOf(100.00); // digite o preço do livro ex:180.59
        var genero = GeneroLivro.FANTASIA; // selecione o genero disponivel no enum
        var titulo = "hp 2"; // escreva o titulo do livro
        var dataPublicacao = LocalDate.of(2004, 8, 16); // digite a data de publicação ex: 2004, 4, 30

        livro.setPreco(preco);
        livro.setGenero(genero);
        livro.setTitulo(titulo);
        livro.setDataPublicacao(dataPublicacao);

        // coloque o id do autor desse livro
        Long id = 2L; // o L logo após o numero é para o java reconhecer que é um Long
        Autor autor = autorRepository.findById(id).orElse(null); // buscando autor pelo id

        livro.setAutor(autor);

        livroRepository.save(livro); // salvando o objeto
    }

    // atualizar livro
    @Test
    public void atualizarLivroTest(){
        // coloque o id do livro
        Long id = 1L; // o L logo após o numero é para o java reconhecer que é um Long

        Optional<Livro> possivelLivro = livroRepository.findById(id);

        // verificar se o livro existe
        if (possivelLivro.isPresent()){
            Livro livroEncontrado = possivelLivro.get();
            System.out.println("Dados do livro");
            System.out.println(livroEncontrado);


            // tire o comentario apenas do que quiser atualizar
//            var preco = BigDecimal.valueOf(0); // digite o preço do livro ex:180.59
//            var genero = GeneroLivro.FANTASIA; // selecione o genero disponivel no enum
//            var titulo = "nome do livro"; // escreva o titulo do livro
//            var dataPublicacao = LocalDate.of(); // digite a data de publicação ex: 2004, 4, 30

//            livroEncontrado.setTitulo(titulo);
//            livroEncontrado.setPreco(preco);
//            livroEncontrado.setDataPublicacao(dataPublicacao);
//            livroEncontrado.setGenero(genero);

            livroRepository.save(livroEncontrado); // atualizando livro
        }
    }

    // atualizar o autor do livro
    @Test
    public void atualizarAutorDoLivroTest(){

        // coloque o id do livro
        Long id = 1L; // o L logo após o numero é para o java reconhecer que é um Long
        var livroParaAtualizar = livroRepository.findById(id).orElse(null);

        // coloque o id do autor desse livro
        Long idAutor = 3L; // o L logo após o numero é para o java reconhecer que é um Long
        Autor autor = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(autor);

        livroRepository.save(livroParaAtualizar); // atualizando o objeto
    }

    // deletar livro por id
    @Test
    public void deletarLivroPorId(){

        // coloque o id do livro
        Long id = 1L; // o L logo após o numero é para o java reconhecer que é um Long

        livroRepository.deleteById(id); // deletando o objeto
    }

    // pesquisar livro por id
    @Test
    public void pesquisarLivroTest(){
        // coloque o id do livro
        Long id = 1L; // o L logo após o numero é para o java reconhecer que é um Long
        Livro livro = livroRepository.findById(id).orElse(null);
        System.out.println("Livro: ");
        System.out.println(livro.getTitulo());

        System.out.println("Autor: ");
        System.out.println(livro.getAutor().getNome());
    }

    // pesquisar livro por titulo
    @Test
    public  void pesquisarPorTitulo(){
        List<Livro> lista = livroRepository.findByTituloContainingIgnoreCase("titulo do livro"); // escreva o titulo
        lista.forEach(System.out::println);

    }

    // pesquisar livro por preço
    @Test
    public  void pesquisarPorPreco(){
        var preco = BigDecimal.valueOf(100.00); // coloque o valor do livro ex: 100.00

        List<Livro> lista = livroRepository.findByPreco(preco);
        lista.forEach(System.out::println);

    }

    // pesquisar livro por data de publicação
    @Test
    public void pesquisarPorAnoTest() {

        // digite o ano que deseja pesquisar
        int ano = 2000;
        LocalDate inicio = LocalDate.of(ano, 1, 1);
        LocalDate fim = LocalDate.of(ano, 12, 31);

        List<Livro> livros = livroRepository.findByDataPublicacaoBetween(inicio, fim);
        livros.forEach(System.out::println);
    }

    // listar todos os livros
    @Test
    public void listarLivroTest(){
        List<Livro> lista = livroRepository.findAll();
        lista.forEach(System.out::println);
    }

}
