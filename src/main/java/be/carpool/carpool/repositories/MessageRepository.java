package be.carpool.carpool.repositories;

import be.carpool.carpool.entities.Level;
import be.carpool.carpool.entities.Message;
import be.carpool.carpool.entities.Ride;
import be.carpool.carpool.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByRideOrderByDatetimeAsc(Ride ride);

    Message findFirstByRideOrderByDatetimeDesc(Ride ride);

//    @Query(
//            value = "SELECT *" +
//                    "FROM ride r" +
//                    "WHERE r.id IN (SELECT DISTINCT ride_id FROM message WHERE sender_id = ?1)",
//            nativeQuery = true
//    )
//    Set<Ride> findRidesWithDiscussion(Long userId);
}