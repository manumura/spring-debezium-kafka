package com.example.demo.account.api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler { // extends ResponseEntityExceptionHandler

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String BAD_REQUEST_URL = "http://localhost/errors/bad-request";
    private static final String INTERNAL_SERVER_ERROR_URL = "http://localhost/errors/internal-server-error";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(f -> "Field '" + f.getField() + "' " + f.getDefaultMessage())
                .toList();
        log.error("MethodArgumentNotValidException: {}", errors);

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("MethodArgumentNotValidException");
        problemDetail.setType(URI.create(BAD_REQUEST_URL));
        problemDetail.setProperties(Map.of("errors", errors));
        return problemDetail;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalArgumentException: {}", e.getMessage(), e);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("IllegalArgumentException");
        problemDetail.setType(URI.create(BAD_REQUEST_URL));
        return problemDetail;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleEntityNotFoundException(EntityNotFoundException e) {
        log.error("EntityNotFoundException: {}", e.getMessage(), e);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("EntityNotFoundException");
        problemDetail.setType(URI.create(BAD_REQUEST_URL));
        return problemDetail;
    }

    @ExceptionHandler(EntityExistsException.class)
    public ProblemDetail handleEntityExistsException(EntityExistsException e) {
        log.error("EntityExistsException: {}", e.getMessage(), e);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("EntityExistsException");
        problemDetail.setType(URI.create(BAD_REQUEST_URL));
        return problemDetail;
    }

    @ExceptionHandler(DataAccessException.class)
    public ProblemDetail handleDataAccessException(DataAccessException e) {
        log.error("DataAccessException: {}", e.getMessage(), e);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        problemDetail.setTitle("DataAccessException");
        problemDetail.setType(URI.create(INTERNAL_SERVER_ERROR_URL));
        return problemDetail;
    }
}
