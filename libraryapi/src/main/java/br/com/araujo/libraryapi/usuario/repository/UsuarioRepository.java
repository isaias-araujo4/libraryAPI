package br.com.araujo.libraryapi.usuario.repository;

import br.com.araujo.libraryapi.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
