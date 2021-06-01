package be.carpool.carpool.models.dtos;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RideSimplifiedDto {
    Long id;
    DestinationSimplifiedDto destination;
}
