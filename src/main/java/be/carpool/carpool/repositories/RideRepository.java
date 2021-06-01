package be.carpool.carpool.repositories;

import be.carpool.carpool.entities.Destination;
import be.carpool.carpool.entities.Level;
import be.carpool.carpool.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Set;

public interface RideRepository extends JpaRepository<Ride, Long> {
    Set<Ride> findAllByDepartureCityAndDestinationAndDepartureDate(String departureCity, Destination destination, LocalDate departureDate);
    Set<Ride> findAllByDepartureCityAndDestination(String departureCity, Destination destination);
    Set<Ride> findAllByDepartureCityAndDepartureDate(String departureCity, LocalDate departureDate);
    Set<Ride> findAllByDestinationAndDepartureDate(Destination destination, LocalDate departureDate);
    Set<Ride> findAllByDepartureCity(String departureCity);
    Set<Ride> findAllByDestination(Destination destination);
    Set<Ride> findAllByDepartureDate(LocalDate departureDate);
}
