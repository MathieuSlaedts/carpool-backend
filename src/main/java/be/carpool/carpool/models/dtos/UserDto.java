package be.carpool.carpool.models.dtos;

import be.carpool.carpool.entities.Level;
import be.carpool.carpool.entities.Message;
import be.carpool.carpool.entities.Review;
import be.carpool.carpool.entities.Ride;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    Long id;
    String firstname;
    String lastname;
    String username;
    String email;
    String phone;
    String country;
    String city;
    String zipcode;
    String street;
    String number;
    LevelSimplifiedDto level;
    List<ReviewSimplifiedDto> reviews;
    List<RideSimplifiedDto> ridesAsConductor;
    List<RideSimplifiedDto> ridesAsPassenger;
    List<MessageSimplifiedDto> messages;
    String token;
    List<String> roles;
}
