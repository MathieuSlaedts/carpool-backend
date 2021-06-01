package be.carpool.carpool;

import be.carpool.carpool.entities.*;
import be.carpool.carpool.mappers.*;
import be.carpool.carpool.models.forms.*;
import be.carpool.carpool.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired private DestinationMapper destinationMapper;
    @Autowired private DestinationRepository destinationRepository;

    @Autowired private LevelMapper levelMapper;
    @Autowired private LevelRepository levelRepository;

    @Autowired private MessageMapper messageMapper;
    @Autowired private MessageRepository messageRepository;

    @Autowired private ReviewMapper reviewMapper;
    @Autowired private ReviewRepository reviewRepository;

    @Autowired private RideMapper rideMapper;
    @Autowired private RideRepository rideRepository;

    @Autowired private UserMapper userMapper;
    @Autowired private UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        /*
         *
         * DESTINATIONS
         *
         * */

        Destination destinationOne = destinationRepository.save(
                destinationMapper.formToEntity(DestinationForm.builder()
                        .id(0L)
                        .name("Zeebruges")
                        .country("Belgium")
                        .city("Brugge")
                        .zipcode("8380")
                        .street("Zeedijk")
                        .number("50")
                        .rideIds(null)
                        .reviewIds(null)
                        .build()));

        Destination destinationTwo = destinationRepository.save(
                destinationMapper.formToEntity(DestinationForm.builder()
                        .id(0L)
                        .name("De Haan")
                        .country("Belgium")
                        .city("De Haan")
                        .zipcode("8421")
                        .street("Zeedijk")
                        .number("50")
                        .rideIds(null)
                        .reviewIds(null)
                        .build()));

        Destination destinationThree = destinationRepository.save(
                destinationMapper.formToEntity(DestinationForm.builder()
                        .id(0L)
                        .name("Oesterdam")
                        .country("Netherlands")
                        .city("Tholen")
                        .zipcode("4691")
                        .street("Oesterdam")
                        .number("")
                        .rideIds(null)
                        .reviewIds(null)
                        .build()));

        Destination destinationFour = destinationRepository.save(
                destinationMapper.formToEntity(DestinationForm.builder()
                        .id(0L)
                        .name("Wissant")
                        .country("France")
                        .city("Wissant")
                        .zipcode("62179")
                        .street("Avenue de la Belle Étoile")
                        .number("28")
                        .rideIds(null)
                        .reviewIds(null)
                        .build()));

        /*
         *
         * LEVELS
         *
         * */

        Level levelOne = levelRepository.save(
                levelMapper.formToEntity(LevelForm.builder()
                        .id(0L)
                        .name("Beginner")
                        .build()));

        Level levelTwo = levelRepository.save(
                levelMapper.formToEntity(LevelForm.builder()
                        .id(0L)
                        .name("Intermediate")
                        .build()));

        Level levelThree = levelRepository.save(
                levelMapper.formToEntity(LevelForm.builder()
                        .id(0L)
                        .name("Advanced")
                        .build()));

        Level levelFour = levelRepository.save(
                levelMapper.formToEntity(LevelForm.builder()
                        .id(0L)
                        .name("Expert")
                        .build()));

        /*
         *
         * USERS
         *
         * */

        User userOne = userRepository.save(
                userMapper.formToEntity(UserForm.builder()
                        .id(0L)
                        .firstname("John")
                        .lastname("Doe")
                        .username("johndoe")
                        .email("john.doe@carpool.kite")
                        .phone("+32 (0)498 30 86 87")
                        .password(passwordEncoder.encode("root"))
                        .country("Belgium")
                        .city("Ixelles")
                        .zipcode("1050")
                        .street("Rue Juliette Wystman")
                        .number("08 B")
                        .levelId(levelOne.getId())
                        .reviewIds(null)
                        .rideAsConductorIds(null)
                        .rideAsPassengerIds(null)
                        .messagesIds(null)
                        .roles(Stream.of("ROLE_ADMIN", "ROLE_USER").collect(Collectors.toSet()))
                        .build()));

        User userTwo = userRepository.save(
                userMapper.formToEntity(UserForm.builder()
                        .id(0L)
                        .firstname("Average")
                        .lastname("Joe")
                        .username("averagejoe")
                        .email("average.joe@carpool.kite")
                        .phone("+32 (0)497 29 75 66")
                        .password(passwordEncoder.encode("root"))
                        .country("Belgium")
                        .city("Ixelles")
                        .zipcode("1050")
                        .street("Chaussée d'Ixelles")
                        .number("15")
                        .levelId(levelTwo.getId())
                        .reviewIds(null)
                        .rideAsConductorIds(null)
                        .rideAsPassengerIds(null)
                        .messagesIds(null)
                        .roles(Stream.of("ROLE_USER").collect(Collectors.toSet()))
                        .build()));

        User userThree = userRepository.save(
                userMapper.formToEntity(UserForm.builder()
                        .id(0L)
                        .firstname("Max")
                        .lastname("Mustermann")
                        .username("maxmustermann")
                        .email("max.mustermann@carpool.kite")
                        .phone("+32 (0)597 25 33 55")
                        .password(passwordEncoder.encode("root"))
                        .country("Belgium")
                        .city("Beuxelles")
                        .zipcode("1000")
                        .street("Place de Londres")
                        .number("25")
                        .levelId(levelThree.getId())
                        .reviewIds(null)
                        .rideAsConductorIds(null)
                        .rideAsPassengerIds(null)
                        .messagesIds(null)
                        .roles(Stream.of("ROLE_USER").collect(Collectors.toSet()))
                        .build()));

        User userFour = userRepository.save(
                userMapper.formToEntity(UserForm.builder()
                        .id(0L)
                        .firstname("Juan")
                        .lastname("dela Cruz")
                        .username("juandelacruz")
                        .email("juan.delacruz@carpool.kite")
                        .phone("+32 (0)597 25 33 55")
                        .country("Belgium")
                        .city("Schaerbeek")
                        .zipcode("1030")
                        .street("Chaussée d'Haecht")
                        .number("08 B")
                        .levelId(levelOne.getId())
                        .reviewIds(null)
                        .rideAsConductorIds(null)
                        .rideAsPassengerIds(null)
                        .messagesIds(null)
                        .password(passwordEncoder.encode("root"))
                        .roles(Stream.of("ROLE_USER").collect(Collectors.toSet()))
                        .build()));

        /*
         *
         * RIDES
         *
         * */

        Ride rideOne = rideRepository.save(
                rideMapper.formToEntity(RideForm.builder()
                        .id(0L)
                        .destinationId(destinationOne.getId())
                        .departureCountry(null)
                        .departureCity("Ixelles")
                        .departureZipcode(null)
                        .departureStreet(null)
                        .departureNumber(null)
                        .departureDate(LocalDate.of(2021, 7, 1))
                        .departureTime(LocalTime.of(9, 0, 0))
                        .returnDate(LocalDate.of(2021, 7, 1))
                        .returnTime(LocalTime.of(20, 0, 0))
                        .seatsNum(2)
                        .pricePerPassenger(BigDecimal.valueOf(10.4))
                        .conductorId(userFour.getId())
                        .passengerIds(Set.of(userOne.getId(), userThree.getId()))
                        .messageIds(null)
                        .build()));

        Ride rideTwo = rideRepository.save(
                rideMapper.formToEntity(RideForm.builder()
                        .id(0L)
                        .destinationId(destinationTwo.getId())
                        .departureCountry(null)
                        .departureCity("Namur")
                        .departureZipcode(null)
                        .departureStreet(null)
                        .departureNumber(null)
                        .departureDate(LocalDate.of(2021, 7, 3))
                        .departureTime(LocalTime.of(8, 30, 0))
                        .returnDate(null)
                        .returnTime(null)
                        .seatsNum(1)
                        .pricePerPassenger(BigDecimal.valueOf(5))
                        .conductorId(userTwo.getId())
                        .passengerIds(Set.of(userOne.getId(), userThree.getId()))
                        .messageIds(null)
                        .build()));

        Ride rideThree = rideRepository.save(
                rideMapper.formToEntity(RideForm.builder()
                        .id(0L)
                        .destinationId(destinationOne.getId())
                        .departureCountry(null)
                        .departureCity("Bruxelles")
                        .departureZipcode(null)
                        .departureStreet(null)
                        .departureNumber(null)
                        .departureDate(LocalDate.of(2021, 7, 3))
                        .departureTime(LocalTime.of(12, 0, 0))
                        .returnDate(null)
                        .returnTime(null)
                        .seatsNum(1)
                        .pricePerPassenger(BigDecimal.valueOf(5))
                        .conductorId(userThree.getId())
                        .passengerIds(Set.of(userOne.getId()))
                        .messageIds(null)
                        .build()));

        Ride rideFour = rideRepository.save(
                rideMapper.formToEntity(RideForm.builder()
                        .id(0L)
                        .destinationId(destinationFour.getId())
                        .departureCountry(null)
                        .departureCity("Schaerbeek")
                        .departureZipcode(null)
                        .departureStreet(null)
                        .departureNumber(null)
                        .departureDate(LocalDate.of(2021, 7, 15))
                        .departureTime(LocalTime.of(8, 15, 0))
                        .returnDate(LocalDate.of(2021, 7, 15))
                        .returnTime(LocalTime.of(20, 30, 0))
                        .seatsNum(3)
                        .pricePerPassenger(BigDecimal.valueOf(6.5))
                        .conductorId(userFour.getId())
                        .passengerIds(Set.of(userOne.getId(), userTwo.getId()))
                        .messageIds(null)
                        .build()));

        /*
         *
         * MESSAGES
         *
         * */

        Message messageOne = messageRepository.save(
                messageMapper.formToEntity(MessageForm.builder()
                        .id(0L)
                        .message("Hi Juan."
                            +" Thank you for your carpooling proposal."
                            +" Where should we meet ?")
                        .datetime(LocalDateTime.of(2021, 6, 29, 9, 35, 0))
                        .senderId(userOne.getId())
                        .rideId(rideOne.getId())
                        .build()));

        Message messageTwo = messageRepository.save(
                messageMapper.formToEntity(MessageForm.builder()
                        .id(0L)
                        .message("Hi. I will pick uou up at your place.")
                        .datetime(LocalDateTime.of(2021, 6, 29, 10, 12, 0))
                        .senderId(userFour.getId())
                        .rideId(rideOne.getId())
                        .build()));

        Message messageThree = messageRepository.save(
                messageMapper.formToEntity(MessageForm.builder()
                        .id(0L)
                        .message("Hi. At what time do you think we will be back in Bxl ?")
                        .datetime(LocalDateTime.of(2021, 7, 02, 7, 03, 0))
                        .senderId(userThree.getId())
                        .rideId(rideTwo.getId())
                        .build()));

        Message messageFour = messageRepository.save(
                messageMapper.formToEntity(MessageForm.builder()
                        .id(0L)
                        .message("Hey, I would like to take an additional bag."
                            +"Is there enough space in the care ?")
                        .datetime(LocalDateTime.of(2021, 6, 29, 9, 35, 0))
                        .senderId(userTwo.getId())
                        .rideId(rideFour.getId())
                        .build()));

        /*
         *
         * REVIEWS
         *
         * */

        Review reviewOne = reviewRepository.save(
                reviewMapper.formToEntity(ReviewForm.builder()
                        .id(0L)
                        .message("The place is nice but very crowded.")
                        .rating(3)
                        .datetime(LocalDateTime.of(2020, 6, 29, 9, 35, 0))
                        .destinationId(destinationOne.getId())
                        .reviewerId(userOne.getId())
                        .build()));

        Review reviewTwo = reviewRepository.save(
                reviewMapper.formToEntity(ReviewForm.builder()
                        .id(0L)
                        .message("Nice place, beautifull view.")
                        .rating(4)
                        .datetime(LocalDateTime.of(2020, 11, 8, 22, 52, 0))
                        .destinationId(destinationOne.getId())
                        .reviewerId(userTwo.getId())
                        .build()));

        Review reviewThree = reviewRepository.save(
                reviewMapper.formToEntity(ReviewForm.builder()
                        .id(0L)
                        .message("No parking spot and the beach is full of trashes :(")
                        .rating(1)
                        .datetime(LocalDateTime.of(2021, 3, 12, 13, 17, 0))
                        .destinationId(destinationThree.getId())
                        .reviewerId(userTwo.getId())
                        .build()));

        Review reviewFour = reviewRepository.save(
                reviewMapper.formToEntity(ReviewForm.builder()
                        .id(0L)
                        .message("RAS")
                        .rating(4)
                        .datetime(LocalDateTime.of(2021, 1, 5, 18, 47, 0))
                        .destinationId(destinationFour.getId())
                        .reviewerId(userFour.getId())
                        .build()));
    }
}
