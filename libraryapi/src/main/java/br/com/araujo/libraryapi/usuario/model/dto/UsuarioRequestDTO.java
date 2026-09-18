package br.com.araujo.libraryapi.usuario.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UsuarioRequestDTO(
        @NotBlank(message = "Campo é obrigatorio")
        String nome,

        @NotBlank(message = "Campo é obrigatorio")
        @Email(message = "o email tem que ser valido")
        String email,

        @NotBlank(message = "Campo é obrigatorio")
        @Size(min = 8, message = "A senha deve ser maior que 8 caracteres")
        String senha,

        List<String> roles
) {
}
