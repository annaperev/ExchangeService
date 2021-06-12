package de.c24.finacc.klt.exception;

public class FailedRequestException extends RuntimeException {
    public FailedRequestException() {
        super("Api call not successful");
    }
}
