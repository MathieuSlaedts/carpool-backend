package be.carpool.carpool.models.forms;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReviewForm {
    @NotNull
    Long id;
    @NotBlank @Length(min = 1, max = 1000)
    String message;
    @NotNull @Min(0) @Max(5)
    Integer rating;
    @NotNull @DateTimeFormat
    LocalDateTime datetime;
    @NotNull
    Long destinationId;
    @NotNull
    Long reviewerId; // userId
}
