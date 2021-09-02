package be.carpool.carpool.controller;

import be.carpool.carpool.exceptions.ElementNotFoundException;
import be.carpool.carpool.models.dtos.ReviewDto;
import be.carpool.carpool.models.forms.ReviewForm;
import be.carpool.carpool.services.CrudService;
import be.carpool.carpool.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/reviews")
public class ReviewController extends AbstractCrudController<ReviewForm, ReviewDto, Long> {
    protected ReviewController(ReviewService service) {
        super(service);
    }

    @GetMapping("/by-destination/{id}")
    public ResponseEntity<List<ReviewDto>> findAllByDestinationId(@PathVariable Long id) throws ElementNotFoundException {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(((ReviewService)service).findAllByDestinationId(id));
    }
}
