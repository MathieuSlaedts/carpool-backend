package be.carpool.carpool.mappers;

import be.carpool.carpool.entities.Message;
import be.carpool.carpool.entities.Review;
import be.carpool.carpool.entities.Ride;
import be.carpool.carpool.entities.User;
import be.carpool.carpool.models.dtos.ReviewSimplifiedDto;
import be.carpool.carpool.models.dtos.RideSimplifiedDto;
import be.carpool.carpool.models.dtos.UserDto;
import be.carpool.carpool.models.dtos.UserSimplifiedDto;
import be.carpool.carpool.models.forms.UserForm;
import be.carpool.carpool.repositories.LevelRepository;
import be.carpool.carpool.repositories.MessageRepository;
import be.carpool.carpool.repositories.ReviewRepository;
import be.carpool.carpool.repositories.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class UserMapper {

    @Autowired
    private LevelMapper levelMapper;
    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RideMapper rideMapper;
    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private MessageRepository messageRepository;

    public User formToEntity(UserForm form) {
        return form == null
                ? null
                : User.builder()
                    .id(0L)
                    .firstname(form.getFirstname())
                    .lastname(form.getLastname())
                    .username(form.getUsername())
                    .email(form.getEmail())
                    .phone(form.getPhone())
                    .level(levelRepository.findById(form.getLevelId()).orElse(null))
                    .password(form.getPassword())
                    .country(form.getCountry())
                    .city(form.getCity())
                    .zipcode(form.getZipcode())
                    .street(form.getStreet())
                    .number(form.getNumber())
                    .reviews(Optional.ofNullable(form.getReviewIds())
                        .orElseGet(Collections::emptyList)
                        .stream()
                        .map(r -> reviewRepository.findById(r).orElse(null))
                        .collect(Collectors.toList()))
                    .ridesAsConductor(Optional.ofNullable(form.getRideAsConductorIds())
                            .orElseGet(Collections::emptyList)
                            .stream()
                            .map(r -> rideRepository.findById(r).orElse(null))
                            .collect(Collectors.toList()))
                    .ridesAsPassenger(Optional.ofNullable(form.getRideAsPassengerIds())
                            .orElseGet(Collections::emptyList)
                            .stream()
                            .map(r -> rideRepository.findById(r).orElse(null))
                            .collect(Collectors.toList()))
                    .messages(Optional.ofNullable(form.getMessagesIds())
                            .orElseGet(Collections::emptyList)
                            .stream()
                            .map(r -> messageRepository.findById(r).orElse(null))
                            .collect(Collectors.toList()))
                    .roles(form.getRoles())
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .build();
    }

    public UserDto entityToDto(User entity) {
        return entity == null
                ? null
                : UserDto.builder()
                    .id(entity.getId())
                    .firstname(entity.getFirstname())
                    .lastname(entity.getLastname())
                    .username(entity.getUsername())
                    .email(entity.getEmail())
                    .phone(entity.getPhone())
                    .country(entity.getCountry())
                    .city(entity.getCity())
                    .zipcode(entity.getZipcode())
                    .street(entity.getStreet())
                    .number(entity.getNumber())
                    .level(levelMapper.entityToSimplifiedDto(entity.getLevel()))
                    .reviews(Optional.ofNullable(entity.getReviews())
                        .orElseGet(Collections::emptyList)
                        .stream()
                        .map(r -> reviewMapper.entityToSimplifiedDto(r))
                        .collect(Collectors.toList()))
                    .ridesAsConductor(Optional.ofNullable(entity.getRidesAsConductor())
                            .orElseGet(Collections::emptyList)
                            .stream()
                            .map(r -> rideMapper.entityToSimplifiedDto(r))
                            .collect(Collectors.toList()))
                    .ridesAsPassenger(Optional.ofNullable(entity.getRidesAsPassenger())
                            .orElseGet(Collections::emptyList)
                            .stream()
                            .map(r -> rideMapper.entityToSimplifiedDto(r))
                            .collect(Collectors.toList()))
                    .messages(Optional.ofNullable(entity.getMessages())
                            .orElseGet(Collections::emptyList)
                            .stream()
                            .map(r -> messageMapper.entityToSimplifiedDto(r))
                            .collect(Collectors.toList()))
                    .roles(entity.getRoles())
                    .build();
    }

    public UserSimplifiedDto entityToSimplifiedDto(User entity) {
        return entity == null
                ? null
                : UserSimplifiedDto.builder()
                    .id(entity.getId())
                    .username(entity.getUsername())
                    .build();
    }
}
