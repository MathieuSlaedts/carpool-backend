package be.carpool.carpool.mappers;

import be.carpool.carpool.entities.Message;
import be.carpool.carpool.models.dtos.MessageDto;
import be.carpool.carpool.models.dtos.MessageSimplifiedDto;
import be.carpool.carpool.models.forms.MessageForm;
import be.carpool.carpool.repositories.RideRepository;
import be.carpool.carpool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    @Autowired private UserMapper userMapper;
    @Autowired private UserRepository userRepository;
    @Autowired private RideMapper rideMapper;
    @Autowired private RideRepository rideRepository;

    public Message formToEntity(MessageForm form) {
        return form == null
                ? null
                : Message.builder()
                    .id(form.getId())
                    .datetime(form.getDatetime())
                    .message(form.getMessage())
                    .sender(userRepository.findById(form.getSenderId()).orElse(null))
                    .ride(rideRepository.findById(form.getRideId()).orElse(null))
                    .build();
    }

    public MessageDto entityToDto(Message entity) {
        return entity == null
                ? null
                : MessageDto.builder()
                    .id(entity.getId())
                    .datetime(entity.getDatetime())
                    .message(entity.getMessage())
                    .sender(userMapper.entityToSimplifiedDto(entity.getSender()))
                    .ride(rideMapper.entityToSimplifiedDto(entity.getRide()))
                    .build();
    }

    public MessageSimplifiedDto entityToSimplifiedDto(Message entity) {
        return entity == null
                ? null
                : MessageSimplifiedDto.builder()
                    .id(entity.getId())
                    .build();
    }
}
