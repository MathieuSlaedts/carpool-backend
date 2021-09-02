package be.carpool.carpool.controller;

import be.carpool.carpool.exceptions.BadRequestException;
import be.carpool.carpool.models.dtos.MessageDto;
import be.carpool.carpool.models.forms.MessageForm;
import be.carpool.carpool.services.CrudService;
import be.carpool.carpool.services.LevelService;
import be.carpool.carpool.services.MessageService;
import be.carpool.carpool.services.MessageServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/messages")
public class MessageController extends AbstractCrudController<MessageForm, MessageDto, Long> {
    protected MessageController(MessageService service) {
        super(service);
    }

    @GetMapping("/by-ride/{id}")
    public ResponseEntity<List<MessageDto>> findByRide(@PathVariable Long id) throws BadRequestException {

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(((MessageService)service).findByRide(id));
    }

    @GetMapping("/first-by-ride/{id}")
    public ResponseEntity<MessageDto> findFirstByRide(@PathVariable Long id) throws BadRequestException {

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(((MessageService)service).findFirstByRide(id));
    }
}
