package com.chibana.currencyfair.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Rodrigo Chibana
 * Date: 02/11/2019
 **/
public class NullTransactionException extends GenericApiException {

    private static final String MESSAGE = "Transaction cannot be null";
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    public NullTransactionException() {
        super(MESSAGE, HTTP_STATUS);
    }

}
