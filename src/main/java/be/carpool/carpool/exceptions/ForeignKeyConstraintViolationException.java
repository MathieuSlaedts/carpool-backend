package be.carpool.carpool.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ForeignKeyConstraintViolationException extends Exception {

    private String message = "Foreign Key Constraint";
    private String description = "";

    public ForeignKeyConstraintViolationException() {
        super();
    }

    public ForeignKeyConstraintViolationException(String description) {
        super();
        this.description = description;
    }

    public ForeignKeyConstraintViolationException(String message, String description) {
        super();
        this.message = description;
        this.description = description;
    }
}
