package be.carpool.carpool.controller;

import be.carpool.carpool.models.dtos.LevelDto;
import be.carpool.carpool.models.forms.LevelForm;
import be.carpool.carpool.services.CrudService;
import be.carpool.carpool.services.LevelService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/levels")
public class LevelController extends AbstractCrudController<LevelForm, LevelDto, Long> {

    public LevelController(LevelService service) {
        super(service);
    }
}
