package org.mysj.commons;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.NoSuchElementException;

@lombok.extern.slf4j.Slf4j
@RestControllerAdvice
@Order
class RestControllerAdvisor {

    @ExceptionHandler({
            IllegalArgumentException.class,
            NoSuchElementException.class,
            HttpMessageNotReadableException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorMessage badRequest(Exception ex) {
        return getErrorMessage(ex, ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorMessage internalServerError(Exception ex) {
        return getErrorMessage(ex,"Oops! We're looking into this!");
    }

    private ErrorMessage getErrorMessage(Exception ex, String errorMessage) {
        log.error(ex.getClass().getName(), ex);
        return ErrorMessage.build(errorMessage);
    }

    private record ErrorMessage(String message, Instant date) {

        static ErrorMessage build(String message) {
            return new ErrorMessage(message, Instant.now());
        }
    }
}


