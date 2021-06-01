package be.carpool.carpool.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ElementAlreadyExistsException extends BadRequestException {

    private final String message = "Element already exists";
    private String description = "";

    public ElementAlreadyExistsException() {
        super();
    }

    public ElementAlreadyExistsException(String description) {
        super();
        this.description = description;
    }
}
