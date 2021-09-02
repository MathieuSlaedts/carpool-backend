package be.carpool.carpool.controller;

import be.carpool.carpool.entities.User;
import be.carpool.carpool.exceptions.CustomAuthenticationException;
import be.carpool.carpool.exceptions.ElementAlreadyExistsException;
import be.carpool.carpool.exceptions.ElementNotFoundException;
import be.carpool.carpool.models.dtos.RideDto;
import be.carpool.carpool.models.dtos.UserDto;
import be.carpool.carpool.models.forms.UserForm;
import be.carpool.carpool.services.UserService;
import be.carpool.carpool.services.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractCrudController<UserForm, UserDto, Long> {

    public UserController(UserService userService) {
        super(userService);
    }

    @GetMapping("/find-by-username/{username}")
    public ResponseEntity<UserDto> findOneByUsername(@PathVariable String username) throws ElementNotFoundException {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(((UserService)service).findOneByUsername(username));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> signUp(@RequestBody UserForm form) throws ElementAlreadyExistsException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(((UserService)service).signUp(form));
    };

    @PostMapping("/sign-in")
    public ResponseEntity<UserDto> signIn(@RequestBody UserForm form) throws CustomAuthenticationException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(((UserService)service).signIn(form));
    };

    @PostMapping("/{id}/book-ride")
    public ResponseEntity<UserDto> bookRide(@RequestBody Long rideId, @PathVariable("id") Long id) throws ElementNotFoundException {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(((UserService)service).bookRide(rideId, id));
    }

    @PostMapping("/{id}/unbook-ride")
    public ResponseEntity<UserDto> unBookRide(@RequestBody Long rideId, @PathVariable("id") Long id) throws ElementNotFoundException {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(((UserService)service).unBookRide(rideId, id));
    }
}