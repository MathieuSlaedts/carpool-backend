package be.carpool.carpool.services;

import be.carpool.carpool.models.dtos.ReviewDto;
import be.carpool.carpool.models.forms.ReviewForm;

public interface ReviewService extends CrudService<ReviewForm, ReviewDto, Long> {
}
