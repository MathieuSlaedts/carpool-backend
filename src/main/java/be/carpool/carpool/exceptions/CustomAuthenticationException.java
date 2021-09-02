package be.carpool.carpool.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomAuthenticationException extends BadRequestException {

    private final String message = "Authentication failed.";
    private final String description = "User and/or password invalid(s).";

    public CustomAuthenticationException() {
        super();
    }
}
