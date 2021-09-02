package be.carpool.carpool.repositories;

import be.carpool.carpool.entities.Destination;
import be.carpool.carpool.entities.Level;
import be.carpool.carpool.entities.Ride;
import be.carpool.carpool.entities.User;
import be.carpool.carpool.models.dtos.RideDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findAllByConductor(User conductor);
    List<Ride> findAllByPassengersId(Long id);
    List<Ride> findAllDistinctByMessagesSenderIdOrderByDepartureDateAscDepartureTimeAsc(Long id);

    // Trajets futures filtré
    @Query("SELECT r" +
            " FROM Ride r" +
            " WHERE departureDate >= CURRENT_DATE" +
            " AND (:departureCity IS NULL OR (:departureCity = r.departureCity))" +
            " AND (:destination IS NULL OR (:destination = r.destination))" +
            " AND (:departureDate IS NULL OR (:departureDate = r.departureDate))" +
            " ORDER BY r.departureDate ASC, r.departureTime ASC")
    List<Ride> findAllInFuture(String departureCity, Destination destination, LocalDate departureDate);


    // Trajets futures
    List<Ride> findAllByDepartureDateAfterOrderByDepartureDateAscDepartureTimeAsc(LocalDate date);

    // Trajets futures en tant que Conducteur
    List<Ride> findAllByDepartureDateAfterAndConductorOrderByDepartureDateAscDepartureTimeAsc(LocalDate date, User conductor);

    // Trajets futures en tant que Passager
    List<Ride> findAllByDepartureDateAfterAndPassengersIdOrderByDepartureDateAscDepartureTimeAsc(LocalDate date, Long id);

    // Trajets passés en tant que Conducteur
    List<Ride> findAllByDepartureDateBeforeAndConductorOrderByDepartureDateDescDepartureTimeDesc(LocalDate date, User conductor);

    // Trajets passés en tant que Passager
    List<Ride> findAllByDepartureDateBeforeAndPassengersIdOrderByDepartureDateDescDepartureTimeDesc(LocalDate date, Long id);

    List<Ride> findAllByDepartureCityAndDestinationAndDepartureDate(String departureCity, Destination destination, LocalDate departureDate);
    List<Ride> findAllByDepartureCityAndDestination(String departureCity, Destination destination);
    List<Ride> findAllByDepartureCityAndDepartureDate(String departureCity, LocalDate departureDate);
    List<Ride> findAllByDestinationAndDepartureDate(Destination destination, LocalDate departureDate);
    List<Ride> findAllByDepartureCity(String departureCity);
    List<Ride> findAllByDestination(Destination destination);
    List<Ride> findAllByDepartureDate(LocalDate departureDate);
}
