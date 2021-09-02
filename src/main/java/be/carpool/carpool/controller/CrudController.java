package be.carpool.carpool.controller;

import be.carpool.carpool.exceptions.BadRequestException;
import be.carpool.carpool.exceptions.ForeignKeyConstraintViolationException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface CrudController<FORM, DTO, ID> {
    ResponseEntity<List<DTO>> findAll();
    ResponseEntity<DTO> findOne(ID id) throws BadRequestException;
    ResponseEntity<DTO> save(FORM form) throws BadRequestException;
    ResponseEntity<DTO> update(FORM form, ID id) throws BadRequestException;
    ResponseEntity<Void> delete(ID id) throws BadRequestException, ForeignKeyConstraintViolationException;
}