package be.carpool.carpool.controller;

import be.carpool.carpool.exceptions.ElementNotFoundException;
import be.carpool.carpool.models.dtos.RideDto;
import be.carpool.carpool.models.forms.RideForm;
import be.carpool.carpool.services.CrudService;
import be.carpool.carpool.services.RideService;
import be.carpool.carpool.services.RideServiceImpl;
import be.carpool.carpool.services.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/rides")
public class RideController extends AbstractCrudController<RideForm, RideDto, Long> {

    protected RideController(RideService service) {
        super(service);
    }


    @GetMapping("/filtered")
    public ResponseEntity<List<RideDto>> findBy(
            @RequestParam(name="departurecity", required=false) String departureCity,
            @RequestParam(name="destination", required=false) Long destinationId,
            @RequestParam(name="departuredate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate departureDate) throws ElementNotFoundException {

        List<RideDto> rides = new ArrayList<>();

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

    @GetMapping("by-messages-sender-id/{id}")
    public ResponseEntity<List<RideDto>> findAllByMessagesSenderId(@PathVariable Long id) throws ElementNotFoundException {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(((RideService)service).findAllByMessagesSenderId(id));
    }

    @GetMapping("/in-future")
    public ResponseEntity<List<RideDto>> findRidesInFuture(
            @RequestParam(required = false) String departureCity,
            @RequestParam(required = false) Long destinationId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate
    ) throws ElementNotFoundException {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(((RideService)service).findRidesInFuture(
                        departureCity,
                        destinationId,
                        departureDate
                ));
    };

    @GetMapping("/in-future/by-conductor/{id}")
    public ResponseEntity<List<RideDto>> findRidesInFutureByConductor(@PathVariable Long id) throws ElementNotFoundException {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(((RideService)service).findRidesInFutureByConductor(id));
    };

    @GetMapping("/in-future/by-passenger/{id}")
    public ResponseEntity<List<RideDto>> findRidesInFutureByPassenger(@PathVariable Long id) throws ElementNotFoundException {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(((RideService)service).findRidesInFutureByPassenger(id));
    };

    @GetMapping("/in-past/by-conductor/{id}")
    public ResponseEntity<List<RideDto>> findRidesInPastByConductor(@PathVariable Long id) throws ElementNotFoundException {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(((RideService)service).findRidesInPastByConductor(id));
    };

    @GetMapping("/in-past/by-passenger/{id}")
    public ResponseEntity<List<RideDto>> findRidesInPastByPassenger(@PathVariable Long id) throws ElementNotFoundException {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(((RideService)service).findRidesInPastByPassenger(id));
    };
}