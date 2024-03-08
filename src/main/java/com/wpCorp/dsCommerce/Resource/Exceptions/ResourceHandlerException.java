package com.wpCorp.dsCommerce.Resource.Exceptions;

import com.wpCorp.dsCommerce.Service.Exceptions.ProductNotFound;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
