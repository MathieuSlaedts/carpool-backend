package be.carpool.carpool.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String firstname;

    @Column(nullable = false)
    String lastname;

    @Column(nullable = false)
    String username;

    @Column(nullable = false, unique = true)
    String email;

    @Column
    String phone;

    @Column
    String password;

    @Column
    String country;

    @Column
    String city;

    @Column
    String zipcode;

    @Column
    String street;

    @Column
    String number;

    @ManyToOne
    Level level;

    @OneToMany(mappedBy = "reviewer")
    Set<Review> reviews;

    @OneToMany(mappedBy = "conductor")
    Set<Ride> ridesAsConductor;

    @ManyToMany
    @JoinTable(
            name = "passenger",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "ride_id"))
    Set<Ride> ridesAsPassenger;

    @OneToMany(mappedBy = "sender")
    Set<Message> messages;

    @ElementCollection()
    Set<String> roles;
}
