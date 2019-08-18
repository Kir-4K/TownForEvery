package com.ontravelsolutions.kostusev.townforevery.exception;

import lombok.Getter;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@ResponseStatus(value = NOT_FOUND)
public class TownNotFoundException extends RuntimeException {

    private Long townId;

    public TownNotFoundException(Long townId) {
        super(format("Town not found: Id=%s", townId));
        this.townId = townId;
    }
}
