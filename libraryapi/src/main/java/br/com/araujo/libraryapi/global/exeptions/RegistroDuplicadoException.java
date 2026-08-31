package br.com.araujo.libraryapi.global.exeptions;

public class RegistroDuplicadoException extends RuntimeException{

    public RegistroDuplicadoException(String message) {
        super(message);
    }
}
