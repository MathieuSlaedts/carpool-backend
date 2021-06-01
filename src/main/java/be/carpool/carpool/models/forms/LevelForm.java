package be.carpool.carpool.models.forms;

import be.carpool.carpool.models.dtos.UserSimplifiedDto;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class LevelForm {
    @NotNull
    Long id;
    @NotBlank @Length(min = 3, max = 50)
    String name;
    String description;
    Set<Long> userIds;
}
