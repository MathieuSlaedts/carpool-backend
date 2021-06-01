package be.carpool.carpool.services;

import be.carpool.carpool.exceptions.BadRequestException;
import be.carpool.carpool.exceptions.ForeignKeyConstraintViolationException;

import java.util.Set;

public interface CrudService<FORM, DTO, ID> {
    Set<DTO> findAll();
    DTO findOne(ID id) throws BadRequestException;
    DTO save(FORM form) throws BadRequestException;
    DTO update(FORM form, ID id) throws BadRequestException;
    void delete(ID id) throws BadRequestException, ForeignKeyConstraintViolationException;
}