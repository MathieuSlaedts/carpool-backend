package be.carpool.carpool.reports;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GlobalValidationError {
    String name;
    String description;
}
