package com.chibana.currencyfair.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Rodrigo Chibana
 * Date: 02/11/2019
 **/
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NullTransactionException extends Exception {

    private static final String MESSAGE = "Transaction cannot be null";

    public NullTransactionException() {
        super(MESSAGE);
    }
}
