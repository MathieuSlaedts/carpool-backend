package be.carpool.carpool.reports;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class FieldValidationError {
    String field;
    String description;
}
