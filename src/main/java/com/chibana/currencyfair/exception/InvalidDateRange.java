package com.chibana.currencyfair.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Rodrigo Chibana
 * Date: 02/11/2019
 **/
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDateRange extends Exception {

    private static final String MESSAGE = "Invalid date ranges";

    public InvalidDateRange() {
        super(MESSAGE);
    }
}
