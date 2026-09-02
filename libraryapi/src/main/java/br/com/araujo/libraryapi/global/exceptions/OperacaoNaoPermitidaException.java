package br.com.araujo.libraryapi.global.exceptions;

public class OperacaoNaoPermitidaException extends  RuntimeException{
    public OperacaoNaoPermitidaException(String message) {
        super(message);
    }
}
