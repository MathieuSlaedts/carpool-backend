package be.carpool.carpool.models.forms;

import be.carpool.carpool.models.dtos.ReviewSimplifiedDto;
import be.carpool.carpool.models.dtos.RideSimplifiedDto;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DestinationForm {
    @NotNull
    Long id;
    @NotBlank @Length(min = 3, max = 50)
    String name;
    @NotBlank @Length(min = 3, max = 50)
    String country;
    @NotBlank @Length(min = 3, max = 50)
    String city;
    @NotBlank @Length(min = 1, max = 50)
    String zipcode;
    @NotBlank @Length(min = 3, max = 100)
    String street;
    @NotBlank @Length(min = 1, max = 50)
    String number;
    List<Long> rideIds;
    List<Long> reviewIds;
}
