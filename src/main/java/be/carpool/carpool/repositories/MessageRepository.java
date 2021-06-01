package be.carpool.carpool.repositories;

import be.carpool.carpool.entities.Level;
import be.carpool.carpool.entities.Message;
import be.carpool.carpool.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface MessageRepository extends JpaRepository<Message, Long> {

    Set<Message> findByRideOrderByDatetimeAsc(Ride ride);
}