package be.carpool.carpool.models.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DestinationSimplifiedDto {
    Long id;
    String name;
}
