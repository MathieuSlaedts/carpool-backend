package be.carpool.carpool.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WrongPathVariableTypeException extends BadRequestException {

    private final String message = "Wrong type of Path Variable.";

    public WrongPathVariableTypeException() {
        super();
    }
}
