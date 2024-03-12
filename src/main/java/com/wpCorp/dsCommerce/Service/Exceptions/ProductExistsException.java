package com.wpCorp.dsCommerce.Service.Exceptions;

public class ProductExistsException extends RuntimeException {
    public ProductExistsException(String message) {
        super(message);
    }
}
