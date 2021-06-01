package be.carpool.carpool.services;

import be.carpool.carpool.entities.Destination;
import be.carpool.carpool.entities.Message;
import be.carpool.carpool.entities.Ride;
import be.carpool.carpool.entities.User;
import be.carpool.carpool.exceptions.BadRequestException;
import be.carpool.carpool.exceptions.ElementNotFoundException;
import be.carpool.carpool.exceptions.ForeignKeyConstraintViolationException;
import be.carpool.carpool.mappers.DestinationMapper;
import be.carpool.carpool.mappers.MessageMapper;
import be.carpool.carpool.mappers.RideMapper;
import be.carpool.carpool.mappers.UserMapper;
import be.carpool.carpool.models.dtos.RideDto;
import be.carpool.carpool.models.forms.RideForm;
import be.carpool.carpool.repositories.DestinationRepository;
import be.carpool.carpool.repositories.MessageRepository;
import be.carpool.carpool.repositories.RideRepository;
import be.carpool.carpool.repositories.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RideServiceImpl implements RideService {

//    @Autowired private RideMapper rideMapper;
//    @Autowired private RideRepository rideRepository;
//
//    @Autowired private UserMapper userMapper;
//    @Autowired private UserRepository userRepository;
//
//    @Autowired private DestinationMapper destinationMapper;
//    @Autowired private DestinationRepository destinationRepository;
//
//    @Autowired private MessageMapper messageMapper;
//    @Autowired private MessageRepository messageRepository;

    private final RideMapper rideMapper;
    private final RideRepository rideRepository;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final DestinationMapper destinationMapper;
    private final DestinationRepository destinationRepository;
    private final MessageMapper messageMapper;
    private final MessageRepository messageRepository;

    public RideServiceImpl(
            RideMapper rideMapper,
            RideRepository rideRepository,
            UserMapper userMapper,
            UserRepository userRepository,
            DestinationMapper destinationMapper,
            DestinationRepository destinationRepository,
            MessageMapper messageMapper,
            MessageRepository messageRepository
    ) {
        this.rideMapper = rideMapper;
        this.rideRepository = rideRepository;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.destinationMapper = destinationMapper;
        this.destinationRepository = destinationRepository;
        this.messageMapper = messageMapper;
        this.messageRepository = messageRepository;
    }

    @Override
    public Set<RideDto> findAll() {
        return rideRepository.findAll()
                .stream()
                .map(r -> rideMapper.entityToDto(r))
                .collect(Collectors.toSet());
    }

    @Override
    public RideDto findOne(Long id) throws BadRequestException {
        if(id == null)
            throw new IllegalArgumentException();

        Ride ride = rideRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Ride with id ["+id+"] not found"));

        return rideMapper.entityToDto(ride);
    }

    @Override
    @Transactional
    public RideDto save(RideForm form) throws BadRequestException {
        if(form == null)
            throw new IllegalArgumentException();

        Ride ride = rideRepository.save(
                rideMapper.formToEntity(form));

        return rideMapper.entityToDto(ride);
    }

    @Override
    @Transactional
    public RideDto update(RideForm form, Long id) throws BadRequestException {
        if(form == null || id == null)
            throw new IllegalArgumentException();

        Ride ride = rideRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Ride with id ["+id+"] not found"));

        if(form.getDestinationId() != null)
            ride.setDestination(destinationRepository.findById(form.getDestinationId())
                    .orElseThrow(() -> new ElementNotFoundException("Destination with id ["+id+"] not found")));

        if(form.getDepartureCountry() != null)
            ride.setDepartureCountry(form.getDepartureCountry());

        if(form.getDepartureCity() != null)
            ride.setDepartureCity(form.getDepartureCity());

        if(form.getDepartureZipcode() != null)
            ride.setDepartureZipcode(form.getDepartureZipcode());

        if(form.getDepartureStreet() != null)
            ride.setDepartureStreet(form.getDepartureStreet());

        if(form.getDepartureNumber() != null)
            ride.setDepartureNumber(form.getDepartureNumber());

        if(form.getDepartureDate() != null)
            ride.setDepartureDate(form.getDepartureDate());

        if(form.getDepartureTime() != null)
            ride.setDepartureTime(form.getDepartureTime());

        if(form.getReturnDate() != null)
            ride.setReturnDate(form.getReturnDate());

        if(form.getReturnTime() != null)
            ride.setReturnTime(form.getReturnTime());


        if(form.getPricePerPassenger() != null)
            ride.setPricePerPassenger(form.getPricePerPassenger());


        if(form.getSeatsNum() != null)
            ride.setSeatsNum(form.getSeatsNum());


        if(form.getConductorId() != null)
            ride.setConductor(userRepository.findById(form.getConductorId())
                    .orElseThrow(() -> new ElementNotFoundException("User with id ["+id+"] not found")));


        if(form.getPassengerIds() != null) {
            Set<User> passengers = new HashSet<>();
            for(Long passengerId : form.getPassengerIds()) {
                passengers.add(userRepository.findById(passengerId)
                        .orElseThrow(() -> new ElementNotFoundException("User with id ["+passengerId+"] not found")));
            }
            ride.setPassengers(passengers);
        }


        if(form.getMessageIds() != null) {
            Set<Message> messages = new HashSet<>();
            for(Long messageId : form.getMessageIds()) {
                messages.add(messageRepository.findById(messageId)
                        .orElseThrow(() -> new ElementNotFoundException("User with id ["+messageId+"] not found")));
            }
            ride.setMessages(messages);
        }

        return rideMapper.entityToDto(
                rideRepository.save(ride));
    }

    @Override
    @Transactional
    public void delete(Long id) throws BadRequestException, ForeignKeyConstraintViolationException {
        if(id == null)
            throw new IllegalArgumentException();

        Ride ride = rideRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Ride with id ["+id+"] not found"));

        try {
            rideRepository.delete(ride);

        } catch (DataIntegrityViolationException e) {
            if(e.getRootCause() instanceof SQLIntegrityConstraintViolationException) {
                throw new ForeignKeyConstraintViolationException("record could not be deleted", e.getRootCause().toString());
            } else {
                throw e;
            }
        }
    }

    @Override
    public Set<RideDto> findAllByDepartureCityAndDestinationAndDepartureDate(String departureCity, Long destinationId, LocalDate departureDate) throws ElementNotFoundException {
        if(departureCity == null && destinationId == null && departureDate == null)
            throw new IllegalArgumentException();

        Destination destination = destinationRepository.findById(destinationId)
                .orElseThrow(() -> new ElementNotFoundException("Destination with id ["+destinationId+"] not found"));

        return rideRepository.findAllByDepartureCityAndDestinationAndDepartureDate(departureCity, destination, departureDate)
                .stream()
                .map(r -> rideMapper.entityToDto(r))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<RideDto> findAllByDepartureCityAndDestination(String departureCity, Long destinationId) throws ElementNotFoundException {
        if(departureCity == null && destinationId == null)
            throw new IllegalArgumentException();

        Destination destination = destinationRepository.findById(destinationId)
                .orElseThrow(() -> new ElementNotFoundException("Destination with id ["+destinationId+"] not found"));

        return rideRepository.findAllByDepartureCityAndDestination(departureCity, destination)
                .stream()
                .map(r -> rideMapper.entityToDto(r))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<RideDto> findAllByDepartureCityAndDepartureDate(String departureCity, LocalDate departureDate) {
        if(departureCity == null && departureDate == null)
            throw new IllegalArgumentException();

        return rideRepository.findAllByDepartureCityAndDepartureDate(departureCity, departureDate)
                .stream()
                .map(r -> rideMapper.entityToDto(r))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<RideDto> findAllByDestinationAndDepartureDate(Long destinationId, LocalDate departureDate) throws ElementNotFoundException {
        if(destinationId == null && departureDate == null)
            throw new IllegalArgumentException();

        Destination destination = destinationRepository.findById(destinationId)
                .orElseThrow(() -> new ElementNotFoundException("Destination with id ["+destinationId+"] not found"));

        return rideRepository.findAllByDestinationAndDepartureDate(destination, departureDate)
                .stream()
                .map(r -> rideMapper.entityToDto(r))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<RideDto> findAllByDepartureCity(String departureCity) {
        if(departureCity == null)
            throw new IllegalArgumentException();

        return rideRepository.findAllByDepartureCity(departureCity)
                .stream()
                .map(r -> rideMapper.entityToDto(r))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<RideDto> findAllByDestination(Long destinationId) throws ElementNotFoundException {
        if(destinationId == null)
            throw new IllegalArgumentException();

        Destination destination = destinationRepository.findById(destinationId)
                .orElseThrow(() -> new ElementNotFoundException("Destination with id ["+destinationId+"] not found"));

        return rideRepository.findAllByDestination(destination)
                .stream()
                .map(r -> rideMapper.entityToDto(r))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<RideDto> findAllByDepartureDate(LocalDate departureDate) {
        if(departureDate == null)
            throw new IllegalArgumentException();

        return rideRepository.findAllByDepartureDate(departureDate)
                .stream()
                .map(r -> rideMapper.entityToDto(r))
                .collect(Collectors.toSet());
    }
}
