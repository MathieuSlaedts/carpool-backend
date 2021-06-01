package be.carpool.carpool.services;

import be.carpool.carpool.exceptions.ElementNotFoundException;
import be.carpool.carpool.models.dtos.MessageDto;
import be.carpool.carpool.models.forms.MessageForm;

import java.util.Set;

public interface MessageService extends CrudService<MessageForm, MessageDto, Long> {
    Set<MessageDto> findByRide(Long id) throws ElementNotFoundException;
}
