package be.carpool.carpool.models.dtos;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserSimplifiedDto {
    Long id;
    String username;
}
