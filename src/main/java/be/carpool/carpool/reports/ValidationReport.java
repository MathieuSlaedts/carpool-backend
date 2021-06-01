package be.carpool.carpool.reports;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ValidationReport {
    LocalDateTime timestamp;
    int status;
    String message;
    List<GlobalValidationError> globalErrors;
    List<FieldValidationError> fieldErrors;
    String path;
}
