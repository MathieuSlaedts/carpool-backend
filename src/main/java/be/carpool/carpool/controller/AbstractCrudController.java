package be.carpool.carpool.controller;

import be.carpool.carpool.exceptions.BadRequestException;
import be.carpool.carpool.exceptions.ForeignKeyConstraintViolationException;
import be.carpool.carpool.services.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

public abstract class AbstractCrudController<FORM, DTO, ID> implements CrudController<FORM, DTO, ID> {

    protected final CrudService<FORM, DTO, ID> service;
    protected AbstractCrudController(CrudService<FORM, DTO, ID> service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<DTO>> findAll() {
        System.out.println(service.getClass());
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(service.findAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<DTO> findOne(@PathVariable ID id) throws BadRequestException {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(service.findOne(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<DTO> save(@Valid @RequestBody FORM form) throws BadRequestException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(form));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<DTO> update(@Valid @RequestBody FORM form, @PathVariable ID id) throws BadRequestException {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(service.update(form, id));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) throws BadRequestException, ForeignKeyConstraintViolationException {
        service.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
