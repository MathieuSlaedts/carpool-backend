package be.carpool.carpool.controller;

import be.carpool.carpool.models.dtos.UserDto;
import be.carpool.carpool.models.forms.UserForm;
import be.carpool.carpool.services.UserService;
import be.carpool.carpool.services.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractCrudController<UserForm, UserDto, Long> {

    public UserController(UserService userService) {
        super(userService);
    }
}