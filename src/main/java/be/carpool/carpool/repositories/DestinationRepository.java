package be.carpool.carpool.repositories;

import be.carpool.carpool.entities.Destination;
import be.carpool.carpool.entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DestinationRepository extends JpaRepository<Destination, Long> {

    @Query("SELECT AVG(rating) FROM Review WHERE destination.id = ?1")
    Double getAvgRating(Long id);

    Boolean existsByName(String name);
}
