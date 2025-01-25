package com.F.T.book_service.exception;

public class BookAlreadyExistException extends RuntimeException {
    public BookAlreadyExistException(String s) {
        super(s);
    }
}
