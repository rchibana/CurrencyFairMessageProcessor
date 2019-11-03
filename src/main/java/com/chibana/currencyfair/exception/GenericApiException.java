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

    private String errorMessage;
    private HttpStatus httpStatus;
    private LocalDateTime timestamp;

    public GenericApiException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.timestamp = LocalDateTime.now();
    }

    public GenericApiException(String errorMessage, HttpStatus httpStatus) {
        this(errorMessage);
        this.httpStatus = httpStatus;
    }
}
