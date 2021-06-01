package be.carpool.carpool.services;

import be.carpool.carpool.entities.Message;
import be.carpool.carpool.entities.Review;
import be.carpool.carpool.entities.Ride;
import be.carpool.carpool.entities.User;
import be.carpool.carpool.exceptions.BadRequestException;
import be.carpool.carpool.exceptions.ElementAlreadyExistsException;
import be.carpool.carpool.exceptions.ElementNotFoundException;
import be.carpool.carpool.exceptions.ForeignKeyConstraintViolationException;
import be.carpool.carpool.mappers.*;
import be.carpool.carpool.models.dtos.UserDto;
import be.carpool.carpool.models.forms.UserForm;
import be.carpool.carpool.repositories.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

//    @Autowired private PasswordEncoder passwordEncoder;
//
//    @Autowired private UserMapper userMapper;
//    @Autowired private UserRepository userRepository;
//
//    @Autowired private LevelMapper levelMapper;
//    @Autowired private  LevelRepository levelRepository;
//
//    @Autowired private ReviewMapper reviewMapper;
//    @Autowired private ReviewRepository reviewRepository;
//
//    @Autowired private RideMapper rideMapper;
//    @Autowired private RideRepository rideRepository;
//
//    @Autowired private MessageMapper messageMapper;
//    @Autowired private MessageRepository messageRepository;

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final LevelMapper levelMapper;
    private final LevelRepository levelRepository;
    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;
    private final RideMapper rideMapper;
    private final RideRepository rideRepository;
    private final MessageMapper messageMapper;
    private final MessageRepository messageRepository;

    public UserServiceImpl(
            PasswordEncoder passwordEncoder,
            UserMapper userMapper,
            UserRepository userRepository,
            LevelMapper levelMapper,
            LevelRepository levelRepository,
            ReviewMapper reviewMapper,
            ReviewRepository reviewRepository,
            RideMapper rideMapper,
            RideRepository rideRepository,
            MessageMapper messageMapper,
            MessageRepository messageRepository
    ) {
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.levelMapper = levelMapper;
        this.levelRepository = levelRepository;
        this.reviewMapper = reviewMapper;
        this.reviewRepository = reviewRepository;
        this.rideMapper = rideMapper;
        this.rideRepository = rideRepository;
        this.messageMapper = messageMapper;
        this.messageRepository = messageRepository;
    }

    public Set<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this.userMapper::entityToDto)
                .collect(Collectors.toSet());
    }

    public UserDto findOne(Long id) throws BadRequestException {
        if(id == null)
            throw new IllegalArgumentException();

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("User with id ["+id+"] not found"));

        return userMapper.entityToDto(user);
    }

    public UserDto save(UserForm form) throws ElementAlreadyExistsException {
        if(form == null)
            throw new IllegalArgumentException();

        if(userRepository.existsByEmail(form.getEmail()))
            throw new ElementAlreadyExistsException("User with email ["+form.getEmail()+"] already exists");

        form.setPassword(passwordEncoder.encode(form.getPassword()));
        form.setRoles(Stream.of("ROLE_USER").collect(Collectors.toSet()));

        User user = userRepository.save(
                userMapper.formToEntity(form));

        return userMapper.entityToDto(user);
    }

    public UserDto update(UserForm form, Long id) throws ElementNotFoundException {
        if(form == null || id == null)
            throw new IllegalArgumentException();

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("User with id ["+id+"] not found"));

        if(form.getFirstname() != null)
            user.setFirstname(form.getFirstname());

        if(form.getLastname() != null)
            user.setLastname(form.getLastname());

        if(form.getEmail() != null && !form.getEmail().equals(user.getEmail()))
            user.setEmail(form.getEmail());

        if(form.getPhone() != null)
            user.setPhone(form.getPhone());

        if(form.getPassword() != null)
            user.setPassword(passwordEncoder.encode(form.getPassword()));

        if(form.getCountry() != null)
            user.setCountry(form.getCountry());

        if(form.getCity() != null)
            user.setCity(form.getCity());

        if(form.getZipcode() != null)
            user.setZipcode(form.getZipcode());

        if(form.getStreet() != null)
            user.setStreet(form.getZipcode());

        if(form.getNumber() != null)
            user.setNumber(form.getNumber());

        if(form.getLevelId() != null)
            user.setLevel(levelRepository.findById(form.getLevelId())
                    .orElseThrow(() -> new ElementNotFoundException("Level with id [" + form.getLevelId() + "] not found")));

        if(form.getRoles() != null)
            user.setRoles(form.getRoles());

        if(form.getReviewIds() != null) {
            Set<Review> reviews = new HashSet<>();
            for (Long reviewId : form.getReviewIds()) {
                reviews.add(reviewRepository.findById(reviewId)
                    .orElseThrow(() -> new ElementNotFoundException("Review with id [" + reviewId + "] not found")));
            }
            user.setReviews(reviews);
        }

        if(form.getRideAsConductorIds() != null) {
            Set<Ride> ridesAsConductor = new HashSet<>();
            for(Long rideId : form.getRideAsConductorIds()) {
                ridesAsConductor.add(rideRepository.findById(rideId)
                        .orElseThrow(() -> new ElementNotFoundException("Ride with id [" + rideId + "] not found")));
            }
            user.setRidesAsConductor(ridesAsConductor);
        }

        if(form.getRideAsPassengerIds() != null) {
            Set<Ride> ridesAsPassenger = new HashSet<>();
            for (Long rideId : form.getRideAsConductorIds()) {
                ridesAsPassenger.add(rideRepository.findById(rideId)
                        .orElseThrow(() -> new ElementNotFoundException("Ride with id [" + rideId + "] not found")));
            }
            user.setRidesAsPassenger(ridesAsPassenger);
        }

        if(form.getMessagesIds() != null) {
            Set<Message> messages = new HashSet<>();
            for (Long messageId : form.getMessagesIds()) {
                messages.add(messageRepository.findById(messageId)
                        .orElseThrow(() -> new ElementNotFoundException("Message with id [" + messageId + "] not found")));
            }
            user.setMessages(messages);
        }

        return userMapper.entityToDto(
                userRepository.save(user));
    }

    public void delete(Long id) throws ElementNotFoundException, ForeignKeyConstraintViolationException {
        if(id == null)
            throw new IllegalArgumentException();

        if(!userRepository.existsById(id))
            throw new ElementNotFoundException("User with id ["+id+"] not found");

        try {
            userRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            if(e.getRootCause() instanceof SQLIntegrityConstraintViolationException) {
                throw new ForeignKeyConstraintViolationException("record could not be deleted", e.getRootCause().toString());
            } else {
                throw e;
            }
        }
    }

}
