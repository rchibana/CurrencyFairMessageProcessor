package com.chibana.currencyfair.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Rodrigo Chibana
 * Date: 02/11/2019
 **/
public class TransactionNotFoundException extends GenericApiException {

    private static final String MESSAGE = "User not found";
    private static final HttpStatus HTTP_STATUS = HttpStatus.NO_CONTENT;

    public TransactionNotFoundException() {
        super(MESSAGE, HTTP_STATUS);
    }

}
