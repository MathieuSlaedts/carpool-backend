package be.carpool.carpool.models.dtos;

import be.carpool.carpool.entities.Destination;
import be.carpool.carpool.entities.Message;
import be.carpool.carpool.entities.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RideDto {
    Long id;
    String departureCountry;
    String departureCity;
    String departureZipcode;
    String departureStreet;
    String departureNumber;
    LocalDate departureDate;
    LocalTime departureTime;
    LocalDate returnDate;
    LocalTime returnTime;
    BigDecimal pricePerPassenger;
    int seatsNum;
    DestinationSimplifiedDto destination;
    UserSimplifiedDto conductor;
    List<UserSimplifiedDto> passengers;
    List<MessageSimplifiedDto> messages;
}
