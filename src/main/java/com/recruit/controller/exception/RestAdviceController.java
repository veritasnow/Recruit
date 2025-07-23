package com.recruit.controller.exception;

import java.sql.SQLDataException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestControllerAdvice
public class RestAdviceController {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> runtimeHandle(RuntimeException e) {
        log.error("runtimeHandle. e: " + e.getMessage());
        return new ResponseEntity<>("일반 runtimeExceiton..", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArguHandle(IllegalArgumentException e) {
        log.error("illegalArguHandle. e: " + e.getMessage());
        return new ResponseEntity<>("잘못된 인자", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> notAllowedHandle(HttpRequestMethodNotSupportedException e) {
        log.error("notAllowedHandle. e: " + e.getMessage());
        return new ResponseEntity<>("잘못된 method", HttpStatus.METHOD_NOT_ALLOWED);
    }
    
    @ExceptionHandler(SQLDataException.class)
    public ResponseEntity<String> sqlDataEexceptionHandle(SQLDataException e) {
        log.error("sqlDataEexceptionHandle. e: " + e.getMessage());
        return new ResponseEntity<>("sql 에러발생", HttpStatus.BAD_REQUEST);
    }    
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandle(Exception e) {
        log.error("exceptionHandle. e: " + e.getMessage());
        return new ResponseEntity<>("exception", HttpStatus.NOT_EXTENDED);
    }

}
