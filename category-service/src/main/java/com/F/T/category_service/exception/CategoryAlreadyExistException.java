package com.F.T.category_service.exception;

public class CategoryAlreadyExistException extends RuntimeException {
    public CategoryAlreadyExistException(String s) {
        super(s);
    }
}
