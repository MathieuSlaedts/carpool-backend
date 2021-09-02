package be.carpool.carpool.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class User implements UserDetails {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id Long id;

    @Column(nullable = false) String firstname;
    @Column(nullable = false) String lastname;

    @Column(nullable = false, unique = true) String username;
    @Column(nullable = false, unique = true) String email;
    @Column(nullable = false) String password;

    @Column String phone;
    @Column String country;
    @Column String city;
    @Column String zipcode;
    @Column String street;
    @Column String number;

    @ManyToOne
    Level level;

    @OneToMany(mappedBy = "reviewer")
    List<Review> reviews;

    @OneToMany(mappedBy = "conductor")
    List<Ride> ridesAsConductor;

    @ManyToMany
    @JoinTable(
            name = "passenger",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "ride_id"))
    List<Ride> ridesAsPassenger;

    @OneToMany(mappedBy = "sender")
    List<Message> messages;

    @ElementCollection(fetch = FetchType.EAGER)
    List<String> roles;

    @Column(nullable = false) private Boolean accountNonExpired;
    @Column(nullable = false) private Boolean accountNonLocked;
    @Column(nullable = false) private Boolean credentialsNonExpired;
    @Column(nullable = false) private Boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isAccountNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
