package be.carpool.carpool.mappers;

import be.carpool.carpool.entities.Level;
import be.carpool.carpool.models.dtos.LevelDto;
import be.carpool.carpool.models.dtos.LevelSimplifiedDto;
import be.carpool.carpool.models.forms.LevelForm;
import be.carpool.carpool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LevelMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;

    public Level formToEntity(LevelForm form) {
        return form == null
                ? null
                : Level.builder()
                    .id(form.getId())
                    .name(form.getName())
                    .description(form.getDescription())
                    .users(Optional.ofNullable(form.getUserIds())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(u -> userRepository.findById(u).orElse(null))
                        .collect(Collectors.toSet()))
                    .build();
    }

    public LevelDto entityToDto(Level entity) {
        return entity == null
                ? null
                : LevelDto.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .description(entity.getDescription())
                    .users(Optional.ofNullable(entity.getUsers())
                            .orElseGet(Collections::emptySet)
                            .stream()
                            .map(userMapper::entityToSimplifiedDto)
                            .collect(Collectors.toSet()))
                    .build();
    }

    public LevelSimplifiedDto entityToSimplifiedDto(Level entity) {
        return entity == null
                ? null
                : LevelSimplifiedDto.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .build();
    }
}
