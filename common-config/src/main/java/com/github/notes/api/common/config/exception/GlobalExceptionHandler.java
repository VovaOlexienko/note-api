package com.github.notes.api.common.config.exception;

import com.github.notes.api.common.config.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto exception(RuntimeException ex) {
        log.error(ex.getMessage(), ex);
        return new ErrorDto(ex.getMessage());
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class, MethodArgumentNotValidException.class, ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto validationException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ErrorDto(ex.getMessage());
    }
}