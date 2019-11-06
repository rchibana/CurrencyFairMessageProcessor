package com.chibana.currencyfair.controller;

import com.chibana.currencyfair.dto.ApiErrorResponse;
import com.chibana.currencyfair.exception.GenericApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.text.ParseException;

/**
 * Created by Rodrigo Chibana
 * Date: 02/11/2019
 **/
@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class, ParseException.class})
    public ResponseEntity<Object> constraintViolationException(Exception ex) {

        final ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST);
        apiErrorResponse.setErrorMessage(ex.getMessage());

        return buildResponseEntity(apiErrorResponse);
    }

    @ExceptionHandler(value = GenericApiException.class)
    public ResponseEntity<Object> genericApiException(GenericApiException ex) {
        return buildResponseEntity(new ApiErrorResponse(ex));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> generalException(Exception ex) {
        return buildResponseEntity(new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiErrorResponse apiErrorResponse) {
        return new ResponseEntity<>(apiErrorResponse, apiErrorResponse.getStatusCode());
    }

}
