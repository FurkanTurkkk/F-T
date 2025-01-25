package com.F.T.book_service.exceptionhandler;

import com.F.T.book_service.exception.BookAlreadyExistException;
import com.F.T.book_service.exception.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookAlreadyExistException.class)
    public ResponseEntity<String> handleBookAlreadyExistException(BookAlreadyExistException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FOUND);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }
}
