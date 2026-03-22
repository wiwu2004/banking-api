package com.wiwu.bankaapi.exception;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;



@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request){
        Map<String,String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> erros.put(error.getField(), error.getDefaultMessage()));

        ErroResponse erro = new ErroResponse();
        erro.setTimestamp(LocalDateTime.now());
        erro.setStatus(HttpStatus.BAD_REQUEST.value());
        erro.setError(HttpStatus.BAD_REQUEST.name());
        erro.setMessage("Erro de validação");
        erro.setPath(request.getRequestURI());
        erro.setErros(erros);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(ContaNaoEncontradaException.class)
    public ResponseEntity<ErroResponse> handleContaNaoEncontrada(ContaNaoEncontradaException ex, HttpServletRequest request) {

        ErroResponse erro = new ErroResponse();
        erro.setTimestamp(LocalDateTime.now());
        erro.setStatus(HttpStatus.NOT_FOUND.value());
        erro.setError(HttpStatus.NOT_FOUND.name());
        erro.setMessage(ex.getMessage());
        erro.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handleException(Exception ex, HttpServletRequest request) {

        ErroResponse erro = new ErroResponse();
        erro.setTimestamp(LocalDateTime.now());
        erro.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        erro.setError(HttpStatus.INSUFFICIENT_STORAGE.name());
        erro.setMessage(ex.getMessage());
        erro.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}