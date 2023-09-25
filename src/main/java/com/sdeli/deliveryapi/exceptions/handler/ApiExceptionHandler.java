package com.sdeli.deliveryapi.exceptions.handler;

import com.sdeli.deliveryapi.exceptions.EntityAlreadyExistsException;
import com.sdeli.deliveryapi.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
import java.util.List;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        ProblemType problemType = ProblemType.INVALID_DATA;
        String detail = "One or more fields are invalid. Correct the mistakes and try again.";

        List<Problem.Field> problemFields = ex.getBindingResult()
                .getFieldErrors().stream()
                .map(fieldError -> {
                    String message = messageSource
                            .getMessage(fieldError, LocaleContextHolder.getLocale());

                    return Problem.Field.builder()
                            .name(fieldError.getField())
                            .message(message)
                            .build();
                }).toList();

        Problem problem = problemBuilder(problemType, status, detail)
                .fields(problemFields)
                .build();

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

    @ExceptionHandler(EntityAlreadyExistsException.class)
    private ResponseEntity<?> handleEntityAlreadyExists(
            EntityAlreadyExistsException ex,
            WebRequest request
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.RESOURCE_ALREADY_EXISTS;

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
