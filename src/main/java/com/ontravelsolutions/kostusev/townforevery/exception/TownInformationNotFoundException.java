package com.ontravelsolutions.kostusev.townforevery.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND)
public class TownInformationNotFoundException extends RuntimeException {

    public TownInformationNotFoundException() {
        super("Information not found");
    }
}
