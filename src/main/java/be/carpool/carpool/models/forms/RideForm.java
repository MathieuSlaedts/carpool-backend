package be.carpool.carpool.models.forms;

import be.carpool.carpool.constraints.RideEndDateTimeAfterStartDateTime;
import be.carpool.carpool.models.dtos.DestinationSimplifiedDto;
import be.carpool.carpool.models.dtos.MessageSimplifiedDto;
import be.carpool.carpool.models.dtos.UserSimplifiedDto;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
//@RideEndDateTimeAfterStartDateTime
public class RideForm {
    @NotNull
    Long id;
    @Length(min = 3, max = 50)
    String departureCountry;
    @NotBlank @Length(min = 3, max = 50)
    String departureCity;
    @Length(min = 1, max = 50)
    String departureZipcode;
    @Length(min = 3, max = 100)
    String departureStreet;
    @Length(min = 1, max = 50)
    String departureNumber;
    @NotNull @Future @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate departureDate;
    @NotNull @DateTimeFormat(pattern = "HH:mm:ss")
    LocalTime departureTime;
    @Future @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate returnDate;
    @DateTimeFormat(pattern = "HH:mm:ss")
    LocalTime returnTime;
    BigDecimal pricePerPassenger;
    Integer seatsNum;
    @NotNull
    Long destinationId;
    @NotNull
    Long conductorId; // id of a user
    List<Long> passengerIds;
    List<Long> messageIds;
}
