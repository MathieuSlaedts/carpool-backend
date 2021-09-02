package be.carpool.carpool.services;

import be.carpool.carpool.entities.Message;
import be.carpool.carpool.entities.Ride;
import be.carpool.carpool.exceptions.ElementNotFoundException;
import be.carpool.carpool.models.dtos.MessageDto;
import be.carpool.carpool.models.forms.MessageForm;

import java.util.List;
import java.util.Set;

public interface MessageService extends CrudService<MessageForm, MessageDto, Long> {
    List<MessageDto> findByRide(Long id) throws ElementNotFoundException;
    MessageDto findFirstByRide(Long id) throws ElementNotFoundException;
}
