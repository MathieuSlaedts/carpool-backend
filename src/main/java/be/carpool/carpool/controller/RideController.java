package be.carpool.carpool.controller;

import be.carpool.carpool.exceptions.ElementNotFoundException;
import be.carpool.carpool.models.dtos.RideDto;
import be.carpool.carpool.models.forms.RideForm;
import be.carpool.carpool.services.CrudService;
import be.carpool.carpool.services.RideService;
import be.carpool.carpool.services.RideServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/rides")
public class RideController extends AbstractCrudController<RideForm, RideDto, Long> {

    protected RideController(RideService service) {
        super(service);
    }


    @GetMapping("/filtered")
    public ResponseEntity<Set<RideDto>> findBy(
            @RequestParam(name="departurecity", required=false) String departureCity,
            @RequestParam(name="destination", required=false) Long destinationId,
            @RequestParam(name="departuredate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate departureDate) throws ElementNotFoundException {

        Set<RideDto> rides = new HashSet<>();

        if (departureCity != null && destinationId != null && departureDate != null)
            rides = ((RideService)service)
                    .findAllByDepartureCityAndDestinationAndDepartureDate(
                            departureCity,
                            destinationId,
                            departureDate
                    );
        else if (departureCity != null && destinationId != null)
            rides = ((RideService)service)
                    .findAllByDepartureCityAndDestination(
                            departureCity,
                            destinationId
                    );
        else if (destinationId != null && departureDate != null)
            rides = ((RideService)service)
                    .findAllByDestinationAndDepartureDate(
                            destinationId,
                            departureDate
                    );
        else if (departureCity != null && departureDate != null)
            rides = ((RideService)service)
                    .findAllByDepartureCityAndDepartureDate(
                            departureCity,
                            departureDate
                    );
        else if (departureCity != null)
            rides = ((RideService)service)
                    .findAllByDepartureCity(departureCity);
        else if (destinationId != null)
            rides = ((RideService)service)
                    .findAllByDestination(destinationId);
        else if (departureDate != null)
            rides = ((RideService)service)
                    .findAllByDepartureDate(departureDate);
        else
            rides = service.findAll();

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(rides);
    }
}