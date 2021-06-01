package be.carpool.carpool.services;

import be.carpool.carpool.entities.Review;
import be.carpool.carpool.exceptions.BadRequestException;
import be.carpool.carpool.exceptions.ElementNotFoundException;
import be.carpool.carpool.exceptions.ForeignKeyConstraintViolationException;
import be.carpool.carpool.mappers.DestinationMapper;
import be.carpool.carpool.mappers.ReviewMapper;
import be.carpool.carpool.mappers.UserMapper;
import be.carpool.carpool.models.dtos.ReviewDto;
import be.carpool.carpool.models.forms.ReviewForm;
import be.carpool.carpool.repositories.DestinationRepository;
import be.carpool.carpool.repositories.ReviewRepository;
import be.carpool.carpool.repositories.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

//    @Autowired private ReviewMapper reviewMapper;
//    @Autowired private ReviewRepository reviewRepository;
//
//    @Autowired private DestinationMapper destinationMapper;
//    @Autowired private DestinationRepository destinationRepository;
//
//    @Autowired private UserMapper userMapper;
//    @Autowired private UserRepository userRepository;

    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;
    private final DestinationMapper destinationMapper;
    private final DestinationRepository destinationRepository;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public ReviewServiceImpl(
            ReviewMapper reviewMapper,
            ReviewRepository reviewRepository,
            DestinationMapper destinationMapper,
            DestinationRepository destinationRepository,
            UserMapper userMapper,
            UserRepository userRepository) {
        this.reviewMapper = reviewMapper;
        this.reviewRepository = reviewRepository;
        this.destinationMapper = destinationMapper;
        this.destinationRepository = destinationRepository;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public Set<ReviewDto> findAll() {
        return reviewRepository.findAll()
                .stream()
                .map(r -> reviewMapper.entityToDto(r))
                .collect(Collectors.toSet());
    }

    @Override
    public ReviewDto findOne(Long id) throws BadRequestException {
        if(id == null)
            throw new IllegalArgumentException();

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Review with id ["+id+"] not found"));

        return reviewMapper.entityToDto(review);
    }

    @Override
    public ReviewDto save(ReviewForm form) throws BadRequestException {
        if(form == null)
            throw new IllegalArgumentException();

        Review review = reviewRepository.save(
                reviewMapper.formToEntity(form));

        return reviewMapper.entityToDto(review);
    }

    @Override
    public ReviewDto update(ReviewForm form, Long id) throws BadRequestException {
        if(form == null || id == null)
            throw new IllegalArgumentException();

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Review with id ["+id+"] not found"));

        if(form.getMessage() != null)
            review.setMessage(form.getMessage());

        if(form.getRating() != null)
            review.setRating(form.getRating());

        if(form.getDestinationId() != null)
            review.setDestination(destinationRepository.findById(form.getDestinationId())
                .orElseThrow(() -> new ElementNotFoundException("Destination with id ["+form.getDestinationId()+"] not found")));

        if(form.getReviewerId() != null)
            review.setReviewer(userRepository.findById(form.getReviewerId())
                    .orElseThrow(() -> new ElementNotFoundException("User with id ["+form.getReviewerId()+"] not found")));

        return reviewMapper.entityToDto(
                reviewRepository.save(review));
    }

    @Override
    public void delete(Long id) throws BadRequestException, ForeignKeyConstraintViolationException {
        if(id == null)
            throw new IllegalArgumentException();

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Review with id ["+id+"] not found"));

        try {
            reviewRepository.delete(review);

        } catch (DataIntegrityViolationException e) {
            if(e.getRootCause() instanceof SQLIntegrityConstraintViolationException) {
                throw new ForeignKeyConstraintViolationException("record could not be deleted", e.getRootCause().toString());
            } else {
                throw e;
            }
        }

    }
}
