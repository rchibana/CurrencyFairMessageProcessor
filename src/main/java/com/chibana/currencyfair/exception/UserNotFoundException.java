package com.chibana.currencyfair.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Rodrigo Chibana
 * Date: 02/11/2019
 **/
@ResponseStatus(HttpStatus.NO_CONTENT)
public class UserNotFoundException extends GenericApiException{

    private static final String MESSAGE = "User not found";
    private static final HttpStatus HTTP_STATUS = HttpStatus.NO_CONTENT;

    public UserNotFoundException() {
        super(MESSAGE, HTTP_STATUS);
    }
}
