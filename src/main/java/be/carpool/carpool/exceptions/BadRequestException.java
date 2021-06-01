package be.carpool.carpool.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BadRequestException extends Exception {

    private final String message = "Bad request.";
    private final String description = "";

    public BadRequestException() {
        super();
    }
}
