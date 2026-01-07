package com.proyecto.vdrt.catalogo.config;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.proyecto.vdrt.catalogo.exception.ProductAlreadyExistException;
import com.proyecto.vdrt.catalogo.exception.ProductNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Error de validación");
        response.put("timestamp", new Date());
        response.put("errors", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    } 

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Cuerpo de la solicitud mal formado o tipo de dato incorrecto");
        response.put("timestamp", new Date());
        // ex.getMessage() puede dar detalles técnicos útiles para depurar, pero cuidado en producción
        response.put("message", "Verifique la sintaxis del JSON y los tipos de datos."); 

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleProductNotFoundException(ProductNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("error", "Producto no encontrado");
        response.put("timestamp", new Date());
        response.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ProductAlreadyExistException.class)
    public ResponseEntity<Map<String, Object>> handleProductAlreadyExistException(ProductAlreadyExistException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.CONFLICT.value());
        response.put("error", "Conflicto: El producto ya existe");
        response.put("timestamp", new Date());
        response.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
