package be.carpool.carpool.controller;

import be.carpool.carpool.exceptions.BadRequestException;
import be.carpool.carpool.models.dtos.DestinationDto;
import be.carpool.carpool.models.forms.DestinationForm;
import be.carpool.carpool.services.CrudService;
import be.carpool.carpool.services.DestinationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("/destinations")
public class DestinationController extends AbstractCrudController<DestinationForm, DestinationDto, Long> {
    protected DestinationController(DestinationService service) {
        super(service);
    }

    @GetMapping("/{id}/rating")
    public ResponseEntity<Double> getAvgRating(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(((DestinationService)service).getAvgRating(id));
    }
}
