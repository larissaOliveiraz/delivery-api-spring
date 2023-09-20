package com.sdeli.deliveryapi.exceptions.handler;

import com.sdeli.deliveryapi.exceptions.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        ProblemType problemType = ProblemType.INVALID_DATA;
        String detail = "One or more fields are invalid. Correct the mistakes and try again.";

        Problem problem = problemBuilder(problemType, status, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<?> handleEntityNotFoundException(
            EntityNotFoundException ex,
            WebRequest request
    ) {
        ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
        HttpStatus status = HttpStatus.NOT_FOUND;

        Problem problem = problemBuilder(problemType, status, ex.getMessage()).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    private Problem.ProblemBuilder problemBuilder(
            ProblemType problemType,
            HttpStatusCode status,
            String detail
    ) {
        return Problem.builder()
                .timestamp(OffsetDateTime.now())
                .type(problemType.getPath())
                .status(status.value())
                .title(problemType.getTitle())
                .detail(detail);
    }

}
