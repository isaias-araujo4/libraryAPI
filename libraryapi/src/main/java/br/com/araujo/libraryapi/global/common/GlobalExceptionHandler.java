package br.com.araujo.libraryapi.global.common;

import br.com.araujo.libraryapi.global.exceptions.OperacaoNaoPermitidaException;
import br.com.araujo.libraryapi.global.exceptions.RegistroDuplicadoException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import br.com.araujo.libraryapi.global.dto.ErroResponse;
import br.com.araujo.libraryapi.global.dto.ErroBody;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErroBody> listaErros = fieldErrors
                .stream()
                .map(fieldError -> new ErroBody(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ErroResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", listaErros );
    }

    @ExceptionHandler(RegistroDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResponse handleRegistroDuplicadoException(RegistroDuplicadoException e){
        return ErroResponse.conflito(e.getMessage());
    }

    @ExceptionHandler(OperacaoNaoPermitidaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public  ErroResponse handleOperacaoNaoPermitidaException(OperacaoNaoPermitidaException e){
        return ErroResponse.respostaPadrao(e.getMessage());
    }

    public ErroResponse handleErrosNaoTratados(RuntimeException e){
        return new ErroResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value()
                , "Ocorreu um erro inesperado entre em contato com a administração do sistema"
                ,List.of());
    }
}
