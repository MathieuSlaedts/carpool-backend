package be.carpool.carpool.services;

import be.carpool.carpool.entities.Destination;
import be.carpool.carpool.entities.Review;
import be.carpool.carpool.entities.Ride;
import be.carpool.carpool.exceptions.BadRequestException;
import be.carpool.carpool.exceptions.ElementNotFoundException;
import be.carpool.carpool.exceptions.ForeignKeyConstraintViolationException;
import be.carpool.carpool.mappers.DestinationMapper;
import be.carpool.carpool.mappers.ReviewMapper;
import be.carpool.carpool.mappers.RideMapper;
import be.carpool.carpool.models.dtos.DestinationDto;
import be.carpool.carpool.models.forms.DestinationForm;
import be.carpool.carpool.repositories.DestinationRepository;
import be.carpool.carpool.repositories.ReviewRepository;
import be.carpool.carpool.repositories.RideRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DestinationServiceImpl implements DestinationService {

//    @Autowired
//    private DestinationMapper destinationMapper;
//    @Autowired
//    private DestinationRepository destinationRepository;
//
//    @Autowired
//    private RideMapper rideMapper;
//    @Autowired
//    private RideRepository rideRepository;
//
//    @Autowired
//    private ReviewMapper reviewMapper;
//    @Autowired
//    private ReviewRepository reviewRepository;


    private final DestinationMapper destinationMapper;
    private final DestinationRepository destinationRepository;
    private final RideMapper rideMapper;
    private final RideRepository rideRepository;
    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;

    public DestinationServiceImpl(
            DestinationMapper destinationMapper,
            DestinationRepository destinationRepository,
            RideMapper rideMapper,
            RideRepository rideRepository,
            ReviewMapper reviewMapper,
            ReviewRepository reviewRepository
    ) {
        this.destinationMapper = destinationMapper;
        this.destinationRepository = destinationRepository;
        this.rideMapper = rideMapper;
        this.rideRepository = rideRepository;
        this.reviewMapper = reviewMapper;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Set<DestinationDto> findAll() {

        return destinationRepository.findAll()
                .stream()
                .map(d -> destinationMapper.entityToDto(d))
                .collect(Collectors.toSet());
    }

    @Override
    public DestinationDto findOne(Long id) throws BadRequestException {
        if( id == null)
            throw new IllegalArgumentException();

        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Destination with id ["+id+"] not found"));

        return destinationMapper.entityToDto(destination);
    }

    @Override
    public DestinationDto save(DestinationForm form) throws BadRequestException {
        if( form == null)
            throw new IllegalArgumentException();

        Destination destination = destinationRepository.save(
                destinationMapper.formToEntity(form));

        return destinationMapper.entityToDto(destination);
    }

    @Override
    public DestinationDto update(DestinationForm form, Long id) throws BadRequestException {
        if( form == null || id ==null)
            throw new IllegalArgumentException();

        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Destination with id ["+id+"] not found"));

        if(form.getName() != null)
            destination.setName(form.getName());

        if(form.getCountry() != null)
            destination.setCountry(form.getCountry());

        if(form.getCity() != null)
            destination.setCity(form.getCity());

        if(form.getZipcode() != null)
            destination.setZipcode(form.getZipcode());

        if(form.getStreet() != null)
            destination.setStreet(form.getZipcode());

        if(form.getNumber() != null)
            destination.setNumber(form.getNumber());

        if(form.getRideIds() != null) {
            Set<Ride> rides = new HashSet<>();
            for (Long rideId : form.getRideIds()) {
                rides.add(rideRepository.findById(rideId)
                        .orElseThrow(() -> new ElementNotFoundException("Ride with id ["+rideId+"] not found")));
            }
            destination.setRides(rides);
        }

        if(form.getReviewIds() != null) {
            Set<Review> reviews = new HashSet<>();
            for (Long reviewId : form.getReviewIds()) {
                reviews.add(reviewRepository.findById(reviewId)
                    .orElseThrow(() -> new ElementNotFoundException("Ride with id ["+reviewId+"] not found")));
            }
            destination.setReviews(reviews);
        }

        return destinationMapper.entityToDto(
                destinationRepository.save(destination));
    }

    @Override
    public void delete(Long id) throws BadRequestException, ForeignKeyConstraintViolationException {
        if( id == null)
            throw new IllegalArgumentException();

        Destination destination = destinationRepository.findById(id)
            .orElseThrow(() -> new ElementNotFoundException("Destination with id ["+id+"] not found"));

        try {
            destinationRepository.delete(destination);

        } catch (DataIntegrityViolationException e) {
            if(e.getRootCause() instanceof SQLIntegrityConstraintViolationException) {
                throw new ForeignKeyConstraintViolationException("record could not be deleted", e.getRootCause().toString());
            } else {
                throw e;
            }
        }
    }
}
