package com.chibana.currencyfair.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Created by Rodrigo Chibana
 * Date: 02/11/2019
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class GenericApiException extends Exception {

    private final String errorMessage;
    private final HttpStatus httpStatus;
    private final LocalDateTime timestamp;

    public GenericApiException(String errorMessage, HttpStatus httpStatus) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
        this.timestamp = LocalDateTime.now();
    }

}
