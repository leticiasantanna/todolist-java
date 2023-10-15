package br.com.leticiasantanna.todolist.expection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageException(HttpMessageNotReadableException messageException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageException.getMessage());
    }
}
