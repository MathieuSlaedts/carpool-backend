package be.carpool.carpool.mappers;

import be.carpool.carpool.entities.Review;
import be.carpool.carpool.models.dtos.ReviewDto;
import be.carpool.carpool.models.dtos.ReviewSimplifiedDto;
import be.carpool.carpool.models.forms.ReviewForm;
import be.carpool.carpool.repositories.DestinationRepository;
import be.carpool.carpool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DestinationMapper destinationMapper;
    @Autowired
    private DestinationRepository destinationRepository;

    public Review formToEntity(ReviewForm form) {
        return form == null
                ? null
                : Review.builder()
                    .id(form.getId())
                    .message(form.getMessage())
                    .rating(form.getRating())
                    .datetime(form.getDatetime())
                    .destination(destinationRepository.findById(form.getDestinationId()).orElse(null))
                    .reviewer(userRepository.findById(form.getReviewerId()).orElse(null))
                    .build();
    }

    public ReviewDto entityToDto(Review entity) {
        return entity == null
                ? null
                : ReviewDto.builder()
                    .id(entity.getId())
                    .message(entity.getMessage())
                    .rating(entity.getRating())
                    .datetime(entity.getDatetime())
                    .destination(destinationMapper.entityToSimplifiedDto(entity.getDestination()))
                    .reviewer(userMapper.entityToSimplifiedDto(entity.getReviewer()))
                    .build();
    }

    public ReviewSimplifiedDto entityToSimplifiedDto(Review entity) {
        return entity == null
                ? null
                : ReviewSimplifiedDto.builder()
                    .id(entity.getId())
                    .build();
    }
}
