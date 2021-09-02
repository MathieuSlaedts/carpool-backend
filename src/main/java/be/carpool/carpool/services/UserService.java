package be.carpool.carpool.services;

import be.carpool.carpool.entities.Level;
import be.carpool.carpool.exceptions.CustomAuthenticationException;
import be.carpool.carpool.exceptions.ElementAlreadyExistsException;
import be.carpool.carpool.exceptions.ElementNotFoundException;
import be.carpool.carpool.models.dtos.UserDto;
import be.carpool.carpool.models.forms.UserForm;

import javax.persistence.*;
import java.util.Set;

public interface UserService extends CrudService<UserForm, UserDto, Long> {
    UserDto findOneByUsername(String username) throws ElementNotFoundException;
    UserDto signIn(UserForm form) throws CustomAuthenticationException;
    UserDto signUp(UserForm form) throws ElementAlreadyExistsException;
    UserDto bookRide(Long rideId, Long id) throws ElementNotFoundException;
    UserDto unBookRide(Long rideId, Long id) throws ElementNotFoundException;
}