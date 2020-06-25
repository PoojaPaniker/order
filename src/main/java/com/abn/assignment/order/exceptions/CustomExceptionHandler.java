package com.abn.assignment.order.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println("Here in exception");
        return new ResponseEntity<>(new CustomErrorDetails(new Date(),
                "Argument sent in request is invalid",ex.getLocalizedMessage()),HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println("Here in exception");
        return new ResponseEntity<>(new CustomErrorDetails(new Date(),
                "Http Request is Invalid",ex.getLocalizedMessage()),HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println("Here in parameter missing");
        return new ResponseEntity<>(new CustomErrorDetails(new Date(),
                "Request Parameter is missing",ex.getLocalizedMessage()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public final ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException e,WebRequest webRequest){
        return new ResponseEntity<>(new CustomErrorDetails(new Date(),
                "Order Not found in Repository",webRequest.getDescription(false)),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StockNotAvailableException.class)
    public final ResponseEntity<Object> handleStockNotFoundException(StockNotAvailableException e,WebRequest webRequest){
        return new ResponseEntity<>(new CustomErrorDetails(new Date(),
                "Stock is not available",webRequest.getDescription(false)),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
        return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
