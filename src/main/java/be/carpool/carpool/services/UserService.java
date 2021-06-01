package be.carpool.carpool.services;

import be.carpool.carpool.entities.Level;
import be.carpool.carpool.models.dtos.UserDto;
import be.carpool.carpool.models.forms.UserForm;

import javax.persistence.*;
import java.util.Set;

public interface UserService extends CrudService<UserForm, UserDto, Long> {
}