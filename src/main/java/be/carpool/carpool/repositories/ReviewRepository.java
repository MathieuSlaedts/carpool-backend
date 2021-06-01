package be.carpool.carpool.repositories;

import be.carpool.carpool.entities.Level;
import be.carpool.carpool.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
