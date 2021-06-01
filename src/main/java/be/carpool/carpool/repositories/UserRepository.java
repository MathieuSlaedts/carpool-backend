package be.carpool.carpool.repositories;

import be.carpool.carpool.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);
    User findByUsername(String username);
    List<User> findAllByLevelId(Long id);
}