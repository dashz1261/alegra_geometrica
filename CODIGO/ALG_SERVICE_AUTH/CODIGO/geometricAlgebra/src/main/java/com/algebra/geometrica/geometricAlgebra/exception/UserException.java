package com.algebra.geometrica.geometricAlgebra.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
@Getter @Setter
public class UserException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private HttpStatus status = HttpStatus.PRECONDITION_FAILED;
    private Integer errorCode;

    public UserException(Exception e) {
        super(e);
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
