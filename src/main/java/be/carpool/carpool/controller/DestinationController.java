package be.carpool.carpool.controller;

import be.carpool.carpool.exceptions.BadRequestException;
import be.carpool.carpool.models.dtos.DestinationDto;
import be.carpool.carpool.models.forms.DestinationForm;
import be.carpool.carpool.services.CrudService;
import be.carpool.carpool.services.DestinationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("/destinations")
public class DestinationController extends AbstractCrudController<DestinationForm, DestinationDto, Long> {
    protected DestinationController(DestinationService service) {
        super(service);
    }
}
