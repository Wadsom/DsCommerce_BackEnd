package com.wpCorp.dsCommerce.Resource.Exceptions;

import com.wpCorp.dsCommerce.Service.Exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceHandlerException {

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<StandarError> prodNotFound(HttpServletRequest request, ProductNotFound e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandarError stdErr = new StandarError();
        stdErr.setPath(request.getRequestURI());
        stdErr.setError(e.getMessage());
        stdErr.setMessage("Resource not found!");
        stdErr.setTimeStamp(Instant.now());
        stdErr.setStatus(status.value());
        return ResponseEntity.status(status).body(stdErr);
    }

    @ExceptionHandler(ProductExistsException.class)
    public ResponseEntity<StandarError> prodExists(HttpServletRequest request, ProductExistsException e) {
        HttpStatus sts = HttpStatus.BAD_REQUEST;
        StandarError stdErr = new StandarError();
        stdErr.setError("Product Exception");
        stdErr.setMessage(e.getMessage());
        stdErr.setStatus(sts.value());
        stdErr.setPath(request.getRequestURI());
        stdErr.setTimeStamp(Instant.now());
        return ResponseEntity.status(sts).body(stdErr);
    }

    @ExceptionHandler(CategoryExistsException.class)
    public ResponseEntity<StandarError> categoryNotFound(HttpServletRequest request, CategoryExistsException e) {
        HttpStatus sts = HttpStatus.BAD_REQUEST;
        StandarError stdErr = new StandarError();
        stdErr.setTimeStamp(Instant.now());
        stdErr.setStatus(sts.value());
        stdErr.setPath(request.getRequestURI());
        stdErr.setError(e.getMessage());
        stdErr.setMessage("Category Exception");
        return ResponseEntity.status(sts).body(stdErr);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<StandarError> categoryNotFound(HttpServletRequest request, CategoryNotFoundException e) {
        HttpStatus sts = HttpStatus.NOT_FOUND;
        StandarError stdErr = new StandarError();
        stdErr.setTimeStamp(Instant.now());
        stdErr.setStatus(sts.value());
        stdErr.setPath(request.getRequestURI());
        stdErr.setError(e.getMessage());
        stdErr.setMessage("Category Exception");
        return ResponseEntity.status(sts).body(stdErr);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidateMessage> validateMessage(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus sts = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidateMessage stdErr = new ValidateMessage();
        stdErr.setMessage(e.getMessage());
        stdErr.setError("Valid Exception");
        stdErr.setPath(request.getRequestURI());
        stdErr.setStatus(sts.value());
        stdErr.setTimeStamp(Instant.now());
        for (FieldError field : e.getBindingResult().getFieldErrors()) {
            stdErr.addMessage(field.getField(), field.getDefaultMessage());
        }
        return ResponseEntity.status(sts).body(stdErr);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandarError> dataIntegratViolation(HttpServletRequest request, DatabaseException e) {
        HttpStatus sts = HttpStatus.BAD_REQUEST;
        StandarError stdErr = new StandarError();
        stdErr.setTimeStamp(Instant.now());
        stdErr.setStatus(sts.value());
        stdErr.setPath(request.getRequestURI());
        stdErr.setError(e.getMessage());
        stdErr.setMessage("Database Exception");
        return ResponseEntity.status(sts).body(stdErr);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<StandarError> categoryNotFound(HttpServletRequest request, UserNotFoundException e) {
        HttpStatus sts = HttpStatus.NOT_FOUND;
        StandarError stdErr = new StandarError();
        stdErr.setTimeStamp(Instant.now());
        stdErr.setStatus(sts.value());
        stdErr.setPath(request.getRequestURI());
        stdErr.setError(e.getMessage());
        stdErr.setMessage("User Exception");
        return ResponseEntity.status(sts).body(stdErr);
    }
    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<StandarError> UserExists(HttpServletRequest request, UserExistsException e) {
        HttpStatus sts = HttpStatus.BAD_REQUEST;
        StandarError stdErr = new StandarError();
        stdErr.setTimeStamp(Instant.now());
        stdErr.setStatus(sts.value());
        stdErr.setPath(request.getRequestURI());
        stdErr.setError(e.getMessage());
        stdErr.setMessage("Database Exception");
        return ResponseEntity.status(sts).body(stdErr);
    }

}
