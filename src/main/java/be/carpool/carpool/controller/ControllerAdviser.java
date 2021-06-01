package be.carpool.carpool.controller;
import be.carpool.carpool.exceptions.BadRequestException;
import be.carpool.carpool.exceptions.ElementAlreadyExistsException;
import be.carpool.carpool.exceptions.ForeignKeyConstraintViolationException;
import be.carpool.carpool.reports.ExceptionReport;
import be.carpool.carpool.reports.FieldValidationError;
import be.carpool.carpool.reports.GlobalValidationError;
import be.carpool.carpool.reports.ValidationReport;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerAdviser extends ResponseEntityExceptionHandler {

//    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    protected ExceptionReport SQLIntegrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException ex, HttpServletRequest request) {
//        return ExceptionReport.builder()
//                .timestamp(LocalDateTime.now())
//                .status(HttpStatus.BAD_REQUEST.value())
//                .message("Test")
//                .description(ex.getMessage())
//                .path(request.getRequestURI())
//                .build();
//    }

    @ExceptionHandler({ForeignKeyConstraintViolationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    protected ExceptionReport ForeignKeyConstraintViolationExceptionHandler(ForeignKeyConstraintViolationException ex, HttpServletRequest request) {
        return ExceptionReport.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .description(ex.getDescription())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ExceptionReport BadRequestExceptionHandler(BadRequestException ex, HttpServletRequest request) {
        return ExceptionReport.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .description(ex.getDescription())
                .path(request.getRequestURI())
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        //return super.handleMethodArgumentNotValid(ex, headers, status, request);

        List<GlobalValidationError> globalErrors = new ArrayList<>();
        ex.getGlobalErrors()
                .forEach(globalError -> globalErrors.add(
                        GlobalValidationError.builder()
                            .name(globalError.getObjectName())
                            .description(globalError.getDefaultMessage())
                            .build()));

        List<FieldValidationError> fieldValidationErrors = new ArrayList<>();

        for (FieldError fieldError: ex.getFieldErrors()) {

            FieldValidationError fieldValidationError = FieldValidationError
                    .builder()
                    .field(fieldError.getField())
                    .description(fieldError.getDefaultMessage())
                    .build();

            fieldValidationErrors.add(fieldValidationError);
        }

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .body(ValidationReport.builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                    .message("validation failed")
                    .globalErrors(globalErrors)
                    .fieldErrors(fieldValidationErrors)
                    .path(request.getDescription(false).substring(4))
                    .build());
    }


}
