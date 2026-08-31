package br.com.araujo.libraryapi.global.exeptions;

public class OperacaoNaoPermitidaException extends  RuntimeException{
    public OperacaoNaoPermitidaException(String message) {
        super(message);
    }
}
