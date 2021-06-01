package be.carpool.carpool.models.dtos;

import be.carpool.carpool.entities.Destination;
import be.carpool.carpool.entities.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReviewDto {
    Long id;
    String message;
    int rating;
    LocalDateTime datetime;
    DestinationSimplifiedDto destination;
    UserSimplifiedDto reviewer;
}
