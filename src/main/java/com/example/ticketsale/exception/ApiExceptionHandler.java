package com.example.ticketsale.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { RuntimeException.class })
    protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("error", ex.getMessage());
        HttpStatus code = HttpStatus.INTERNAL_SERVER_ERROR;
        Throwable t = ex;
        if (ex.getCause() != null) {
            t = ex.getCause();
        }
        String className = t.getClass().getName().replaceAll("(\\w+\\.)*", "");
        switch (className) {
            case "NoSuchElementException":
            case "EntityNotFoundException":
                code = HttpStatus.NOT_FOUND;
                break;
            case "IllegalTicketStateException":
                code = HttpStatus.BAD_REQUEST;
                break;
            case "LowBalanceException":
                code = HttpStatus.FORBIDDEN;
        }
        return handleExceptionInternal(ex, responseBody,
                new HttpHeaders(), code, request);
    }
}
