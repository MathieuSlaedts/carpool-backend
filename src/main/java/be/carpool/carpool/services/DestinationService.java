package be.carpool.carpool.services;

import be.carpool.carpool.models.dtos.DestinationDto;
import be.carpool.carpool.models.forms.DestinationForm;

public interface DestinationService extends CrudService<DestinationForm, DestinationDto, Long> {
    Double getAvgRating(Long id);
}
