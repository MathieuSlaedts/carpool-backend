package be.carpool.carpool.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String departureCountry;

    @Column(nullable = false)
    String departureCity;

    @Column
    String departureZipcode;

    @Column
    String departureStreet;

    @Column
    String departureNumber;

    @Column(nullable = false)
    LocalDate departureDate;

    @Column(nullable = false)
    LocalTime departureTime;

    @Column
    LocalDate returnDate;

    @Column
    LocalTime returnTime;

    @Column
    BigDecimal pricePerPassenger;

    @Column
    int seatsNum;

    @ManyToOne
    Destination destination;

    @ManyToOne
    User conductor;

    @ManyToMany(mappedBy = "ridesAsPassenger")
    Set<User> passengers;

    @OneToMany(mappedBy = "ride")
    Set<Message> messages;
}
