package be.carpool.carpool.mappers;

import be.carpool.carpool.entities.Destination;
import be.carpool.carpool.models.dtos.DestinationDto;
import be.carpool.carpool.models.dtos.DestinationSimplifiedDto;
import be.carpool.carpool.models.forms.DestinationForm;
import be.carpool.carpool.repositories.ReviewRepository;
import be.carpool.carpool.repositories.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DestinationMapper {

    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RideMapper rideMapper;
    @Autowired
    private RideRepository rideRepository;

    public Destination formToEntity(DestinationForm form) {
        return form == null
                ? null
                : Destination.builder()
                    .id(form.getId())
                    .name(form.getName())
                    .country(form.getCountry())
                    .city(form.getCity())
                    .zipcode(form.getZipcode())
                    .street(form.getStreet())
                    .number(form.getNumber())
                    .reviews(Optional.ofNullable(form.getReviewIds())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(r -> reviewRepository.findById(r).orElse(null))
                        .collect(Collectors.toSet()))
                    .rides(Optional.ofNullable(form.getReviewIds())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(r -> rideRepository.findById(r).orElse(null))
                        .collect(Collectors.toSet()))
                    .build();
    }

    public DestinationDto entityToDto(Destination entity) {
        return entity == null
                ? null
                : DestinationDto.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .country(entity.getCountry())
                    .city(entity.getCity())
                    .zipcode(entity.getZipcode())
                    .street(entity.getStreet())
                    .number(entity.getNumber())
                    .reviews(Optional.ofNullable(entity.getReviews())
                                .orElseGet(Collections::emptySet)
                                .stream()
                                .map(r -> reviewMapper.entityToSimplifiedDto(r))
                                .collect(Collectors.toSet()))
                    .rides(Optional.ofNullable(entity.getRides())
                            .orElseGet(Collections::emptySet)
                                .stream()
                                .map(r -> rideMapper.entityToSimplifiedDto(r))
                                .collect(Collectors.toSet()))
                    .build();
    }

    public DestinationSimplifiedDto entityToSimplifiedDto(Destination entity) {
        return entity == null
                ? null
                : DestinationSimplifiedDto.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .build();
    }
}
