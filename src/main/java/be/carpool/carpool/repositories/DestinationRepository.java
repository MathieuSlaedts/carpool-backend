package be.carpool.carpool.repositories;

import be.carpool.carpool.entities.Destination;
import be.carpool.carpool.entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
}
