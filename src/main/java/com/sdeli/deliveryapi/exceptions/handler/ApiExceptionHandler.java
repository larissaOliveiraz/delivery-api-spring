package com.sdeli.deliveryapi.exceptions.handler;

import com.sdeli.deliveryapi.exceptions.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

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
            HttpStatus status,
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
