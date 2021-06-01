package be.carpool.carpool.models.forms;

import be.carpool.carpool.models.dtos.RideSimplifiedDto;
import be.carpool.carpool.models.dtos.UserSimplifiedDto;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MessageForm {
    @NotNull
    Long id;
    @NotBlank @Length(min = 3, max = 50)
    String message;
    @NotNull @DateTimeFormat
    LocalDateTime datetime;
    @NotNull
    Long senderId; // id of a user
    @NotNull
    Long rideId;
}
