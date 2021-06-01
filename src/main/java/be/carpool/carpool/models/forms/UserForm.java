package be.carpool.carpool.models.forms;

import be.carpool.carpool.models.dtos.MessageSimplifiedDto;
import be.carpool.carpool.models.dtos.ReviewSimplifiedDto;
import be.carpool.carpool.models.dtos.RideSimplifiedDto;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class UserForm {
    @NotNull
    Long id;
    @NotBlank @Length(min = 3, max = 30)
    String firstname;
    @NotBlank @Length(min = 3, max = 30)
    String lastname;
    @NotBlank @Length(min = 3, max = 30)
    String username;
    @NotBlank @Email
    String email;
    @NotBlank
    String phone;
    @NotBlank @Length(min = 4, max = 30)
    String password;
    @Length(min = 3, max = 50)
    String country;
    @Length(min = 3, max = 50)
    String city;
    @Length(min = 1, max = 50)
    String zipcode;
    @Length(min = 3, max = 100)
    String street;
    @Length(min = 1, max = 50)
    String number;
    Long levelId;
    Set<Long> reviewIds;
    Set<Long> rideAsConductorIds;
    Set<Long> rideAsPassengerIds;
    Set<Long> messagesIds;
    Set<String> roles;
}
