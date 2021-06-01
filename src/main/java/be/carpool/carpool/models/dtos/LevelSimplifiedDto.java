package be.carpool.carpool.models.dtos;

import lombok.*;

import javax.persistence.Column;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class LevelSimplifiedDto {
    Long id;
    String name;
}
