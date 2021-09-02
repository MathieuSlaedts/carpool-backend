package be.carpool.carpool.models.dtos;

import be.carpool.carpool.entities.Review;
import be.carpool.carpool.entities.Ride;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DestinationDto {
    Long id;
    String name;
    String country;
    String city;
    String zipcode;
    String street;
    String number;
    List<RideSimplifiedDto> rides;
    List<ReviewSimplifiedDto> reviews;
}
