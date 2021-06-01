package be.carpool.carpool.controller;

import be.carpool.carpool.models.dtos.ReviewDto;
import be.carpool.carpool.models.forms.ReviewForm;
import be.carpool.carpool.services.CrudService;
import be.carpool.carpool.services.ReviewService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController extends AbstractCrudController<ReviewForm, ReviewDto, Long> {
    protected ReviewController(ReviewService service) {
        super(service);
    }
}
