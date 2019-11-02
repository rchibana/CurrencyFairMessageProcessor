package com.chibana.currencyfair.dto;

import com.chibana.currencyfair.exception.GenericApiException;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Created by Rodrigo Chibana
 * Date: 02/11/2019
 **/
@Data
public class ApiErrorResponse {

    private HttpStatus statusCode;
    private String errorMessage;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;

    public ApiErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiErrorResponse(HttpStatus statusCode) {
        this();
        this.statusCode = statusCode;
    }

    public ApiErrorResponse(HttpStatus statusCode, String errorMessage) {
        this();
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public ApiErrorResponse(GenericApiException ex) {
        this.statusCode = ex.getHttpStatus();
        this.errorMessage = ex.getErrorMessage();
        this.timestamp = ex.getTimestamp();
    }
}
