package be.carpool.carpool.models.dtos;

import be.carpool.carpool.entities.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class LevelDto {
    Long id;
    String name;
    String description;
    Set<UserSimplifiedDto> users;
}
