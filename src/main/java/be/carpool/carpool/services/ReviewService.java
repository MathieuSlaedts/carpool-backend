package be.carpool.carpool.services;

import be.carpool.carpool.entities.Destination;
import be.carpool.carpool.exceptions.ElementNotFoundException;
import be.carpool.carpool.models.dtos.ReviewDto;
import be.carpool.carpool.models.forms.ReviewForm;

import java.util.List;
import java.util.Set;

public interface ReviewService extends CrudService<ReviewForm, ReviewDto, Long> {
    List<ReviewDto> findAllByDestinationId(Long destinationId) throws ElementNotFoundException;
}
