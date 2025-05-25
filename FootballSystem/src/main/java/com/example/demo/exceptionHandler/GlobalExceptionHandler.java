package com.example.demo.exceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
    private String extractUserFriendlyMessage(String technicalMessage) {
        if (technicalMessage.contains("not a valid")) {
            return "nieprawidłowy format danych numerycznych";
        }
        if (technicalMessage.contains("Unrecognized token")) {
            return "nieprawidłowy format JSON";
        }
        return "nieprawidłowe dane wejściowe";
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errorMessage = new StringBuilder("Wystąpiły błędy:\n");

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMessage
                .append("- ")
                .append(error.getField())
                .append(": ")
                .append(error.getDefaultMessage())
                .append("\n");
        });

        return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
    }
    
	@ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String message = ex.getMessage();
        
        if (message.contains("Cannot deserialize value of type `java.lang.Integer`") ||
            message.contains("Cannot deserialize value of type `java.lang.Long`") ||
            message.contains("Cannot deserialize value of type `java.lang.Double`")) {
            return ResponseEntity.badRequest()
                    .body("Błąd formatu danych: sprawdź czy wszystkie pola numeryczne zawierają prawidłowe liczby, a nie tekst.");
        }
        
        if (message.contains("Unrecognized token") || 
            message.contains("was expecting")) {
            return ResponseEntity.badRequest()
                    .body("Błąd formatu JSON: sprawdź czy JSON jest poprawnie sformatowany (brakuje cudzysłowów, przecinków itp.).");
        }
        
        return ResponseEntity.badRequest()
                .body("Błąd parsowania danych JSON: " + extractUserFriendlyMessage(message));
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<String> handleInvalidFormatException(InvalidFormatException ex) {
        return ResponseEntity.badRequest()
                .body("Błąd formatu danych w polu '" + ex.getPath().get(0).getFieldName() + 
                      "': oczekiwano liczby, otrzymano tekst.");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest()
                .body("Nieprawidłowe dane: " + ex.getMessage());
    }
}
