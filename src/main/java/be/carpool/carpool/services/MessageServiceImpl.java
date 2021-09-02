package be.carpool.carpool.services;

import be.carpool.carpool.entities.Message;
import be.carpool.carpool.entities.Ride;
import be.carpool.carpool.exceptions.BadRequestException;
import be.carpool.carpool.exceptions.ElementNotFoundException;
import be.carpool.carpool.exceptions.ForeignKeyConstraintViolationException;
import be.carpool.carpool.mappers.MessageMapper;
import be.carpool.carpool.mappers.RideMapper;
import be.carpool.carpool.mappers.UserMapper;
import be.carpool.carpool.models.dtos.MessageDto;
import be.carpool.carpool.models.forms.MessageForm;
import be.carpool.carpool.repositories.MessageRepository;
import be.carpool.carpool.repositories.RideRepository;
import be.carpool.carpool.repositories.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

//    @Autowired
//    private MessageMapper messageMapper;
//    @Autowired
//    private MessageRepository messageRepository;
//
//    @Autowired
//    private RideMapper rideMapper;
//    @Autowired
//    private RideRepository rideRepository;
//
//    @Autowired
//    private UserMapper userMapper;
//    @Autowired
//    private UserRepository userRepository;

    private final MessageMapper messageMapper;
    private final MessageRepository messageRepository;
    private final RideMapper rideMapper;
    private final RideRepository rideRepository;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public MessageServiceImpl(
            MessageMapper messageMapper,
            MessageRepository messageRepository,
            RideMapper rideMapper,
            RideRepository rideRepository,
            UserMapper userMapper,
            UserRepository userRepository
    ) {
        this.messageMapper = messageMapper;
        this.messageRepository = messageRepository;
        this.rideMapper = rideMapper;
        this.rideRepository = rideRepository;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<MessageDto> findAll() {
        return messageRepository.findAll()
                .stream()
                .map(m -> messageMapper.entityToDto(m))
                .collect(Collectors.toList());
    }

    @Override
    public MessageDto findOne(Long id) throws BadRequestException {
        if(id == null)
            throw new IllegalArgumentException();

        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Message with id ["+id+"] not found"));

        return messageMapper.entityToDto(message);
    }

    @Override
    @Transactional
    public MessageDto save(MessageForm form) throws BadRequestException {
        if(form == null)
            throw new IllegalArgumentException();

        Message message = messageRepository.save(
                messageMapper.formToEntity(form));

        return messageMapper.entityToDto(message);
    }

    @Override
    @Transactional
    public MessageDto update(MessageForm form, Long id) throws BadRequestException {
        if(form == null || id == null)
            throw new IllegalArgumentException();

        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Message with id ["+id+"] not found"));

        if(form.getMessage() != null)
            message.setMessage(form.getMessage());

        if(form.getDatetime() != null)
            message.setDatetime(form.getDatetime());

        if(form.getRideId() != null)
            message.setRide(rideRepository.findById(form.getRideId())
                    .orElseThrow(() -> new ElementNotFoundException("Ride with id ["+form.getRideId()+"] not found")));

        if(form.getSenderId() != null)
            message.setSender(userRepository.findById(form.getSenderId())
                    .orElseThrow(() -> new ElementNotFoundException("User with id ["+form.getSenderId()+"] not found")));

        return messageMapper.entityToDto(
                messageRepository.save(message));
    }

    @Override
    @Transactional
    public void delete(Long id) throws BadRequestException, ForeignKeyConstraintViolationException {
        if(id == null)
            throw new IllegalArgumentException();

        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Message with id ["+id+"] not found"));

        try {
            messageRepository.delete(message);

        } catch (DataIntegrityViolationException e) {
            if(e.getRootCause() instanceof SQLIntegrityConstraintViolationException) {
                throw new ForeignKeyConstraintViolationException("record could not be deleted", e.getRootCause().toString());
            } else {
                throw e;
            }
        }
    }

    @Transactional
    public List<MessageDto> findByRide(Long id) throws ElementNotFoundException {
        if(id == null)
            throw new IllegalArgumentException();

        Ride ride = rideRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Ride with id ["+id+"] not found"));

        return messageRepository.findByRideOrderByDatetimeAsc(ride)
                .stream()
                .map(m -> messageMapper.entityToDto(m))
                .collect(Collectors.toList());
    }

    @Transactional
    public MessageDto findFirstByRide(Long id) throws ElementNotFoundException {
        if(id == null)
            throw new IllegalArgumentException();

        Ride ride = rideRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Ride with id ["+id+"] not found"));

        return messageMapper.entityToDto(
                messageRepository.findFirstByRideOrderByDatetimeDesc(ride));
    }
}
