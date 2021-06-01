package be.carpool.carpool.reports;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class ExceptionReport {
    LocalDateTime timestamp;
    int status;
    String message;
    String description;
    String path;
}
