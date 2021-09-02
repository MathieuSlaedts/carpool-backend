package be.carpool.carpool.repositories;

import be.carpool.carpool.entities.Ride;
import be.carpool.carpool.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String email);
    Optional<User> findByUsername(String username);
    List<User> findAllByLevelId(Long id);
    List<User> findAllByRidesAsPassenger(Ride ride);
}