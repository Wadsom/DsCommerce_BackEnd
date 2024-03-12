package com.wpCorp.dsCommerce.Resource.Exceptions;

import ch.qos.logback.core.status.InfoStatus;
import com.wpCorp.dsCommerce.Entity.ProductEntity;
import com.wpCorp.dsCommerce.Service.Exceptions.ProductExistsException;
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

}
