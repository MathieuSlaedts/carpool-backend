package be.carpool.carpool.models.dtos;

import be.carpool.carpool.entities.Ride;
import be.carpool.carpool.entities.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MessageDto {
    Long id;
    String message;
    LocalDateTime datetime;
    UserSimplifiedDto sender;
    RideSimplifiedDto ride;
}
