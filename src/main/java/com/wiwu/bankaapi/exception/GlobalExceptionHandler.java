package com.wiwu.bankaapi.exception;
import com.wiwu.bankaapi.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;



@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String,String>>> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request){
        Map<String,String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> erros.put(error.getField(), error.getDefaultMessage()));

    ApiResponse<Map<String,String>> response = new ApiResponse<>(400, "Erros de validação", erros);

        return ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler(ContaNaoEncontradaException.class)
    public ResponseEntity<ApiResponse<String>> handleContaNaoEncontrada(ContaNaoEncontradaException ex, HttpServletRequest request) {

        ApiResponse<String> response = new ApiResponse<>(404, ex.getMessage(), null);

        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex, HttpServletRequest request) {

        ApiResponse<String> response = new ApiResponse<>(500, ex.getMessage(), null);

        return ResponseEntity.status(500).body(response);
    }
}