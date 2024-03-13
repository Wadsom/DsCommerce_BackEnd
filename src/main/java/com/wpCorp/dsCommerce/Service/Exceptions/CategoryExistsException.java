package com.wpCorp.dsCommerce.Service.Exceptions;

public class CategoryExistsException extends RuntimeException {
    public CategoryExistsException(String message) {
        super(message);
    }
}
