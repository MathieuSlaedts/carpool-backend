package be.carpool.carpool.repositories;

import be.carpool.carpool.entities.Destination;
import be.carpool.carpool.entities.Level;
import be.carpool.carpool.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByDestination(Destination destination);
}
