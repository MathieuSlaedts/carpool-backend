package be.carpool.carpool.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ElementNotFoundException extends BadRequestException {

    private final String message = "Element not found";
    private String description = "";

    public ElementNotFoundException() {
        super();
    }

    public ElementNotFoundException(String description) {
        super();
        this.description = description;
    }
}
