package be.carpool.carpool.mappers;

import be.carpool.carpool.entities.Ride;
import be.carpool.carpool.models.dtos.RideDto;
import be.carpool.carpool.models.dtos.RideSimplifiedDto;
import be.carpool.carpool.models.forms.RideForm;
import be.carpool.carpool.repositories.DestinationRepository;
import be.carpool.carpool.repositories.MessageRepository;
import be.carpool.carpool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RideMapper {

    @Autowired
    private DestinationMapper destinationMapper;
    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private MessageRepository messageRepository;

    public Ride formToEntity(RideForm form) {
        return form == null
                ? null
                : Ride.builder()
                    .id(form.getId())
                    .departureCountry(form.getDepartureCountry())
                    .departureCity(form.getDepartureCity())
                    .departureZipcode(form.getDepartureZipcode())
                    .departureStreet(form.getDepartureStreet())
                    .departureNumber(form.getDepartureNumber())
                    .departureDate(form.getDepartureDate())
                    .departureTime(form.getDepartureTime())
                    .returnDate(form.getReturnDate())
                    .returnTime(form.getReturnTime())
                    .pricePerPassenger(form.getPricePerPassenger())
                    .seatsNum(form.getSeatsNum())
                    .destination(destinationRepository.findById(form.getDestinationId()).orElse(null))
                    .conductor(userRepository.findById(form.getConductorId()).orElse(null))
                    .passengers(Optional.ofNullable(form.getPassengerIds())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(p -> userRepository.findById(p).orElse(null))
                        .collect(Collectors.toSet()))
                    .messages(Optional.ofNullable(form.getMessageIds())
                            .orElseGet(Collections::emptySet)
                            .stream()
                            .map(m -> messageRepository.findById(m).orElse(null))
                            .collect(Collectors.toSet()))
                    .build();
    }

    public RideDto entityToDto(Ride entity) {
        return entity == null
                ? null
                : RideDto.builder()
                    .id(entity.getId())
                    .departureCountry(entity.getDepartureCountry())
                    .departureCity(entity.getDepartureCity())
                    .departureZipcode(entity.getDepartureZipcode())
                    .departureStreet(entity.getDepartureStreet())
                    .departureNumber(entity.getDepartureNumber())
                    .departureDate(entity.getDepartureDate())
                    .departureTime(entity.getDepartureTime())
                    .returnDate(entity.getReturnDate())
                    .returnTime(entity.getReturnTime())
                    .pricePerPassenger(entity.getPricePerPassenger())
                    .seatsNum(entity.getSeatsNum())
                    .destination(destinationMapper.entityToSimplifiedDto(entity.getDestination()))
                    .conductor(userMapper.entityToSimplifiedDto(entity.getConductor()))
                    .passengers(Optional.ofNullable(entity.getPassengers())
                            .orElseGet(Collections::emptySet)
                            .stream()
                            .map(p -> userMapper.entityToSimplifiedDto(p))
                            .collect(Collectors.toSet()))
                    .messages(Optional.ofNullable(entity.getMessages())
                            .orElseGet(Collections::emptySet)
                            .stream()
                            .map(m -> messageMapper.entityToSimplifiedDto(m))
                            .collect(Collectors.toSet()))
                    .build();
    }

    public RideSimplifiedDto entityToSimplifiedDto(Ride entity) {
        return entity == null
                ? null
                : RideSimplifiedDto.builder()
                    .id(entity.getId())
                    .destination(destinationMapper.entityToSimplifiedDto(entity.getDestination()))
                    .build();
    }
}
