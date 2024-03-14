package com.wpCorp.dsCommerce.Service.Exceptions;

public class UserExistsException extends RuntimeException {
    public UserExistsException(String message) {
        super(message);
    }
}
