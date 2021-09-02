package be.carpool.carpool.services;

import be.carpool.carpool.entities.Level;
import be.carpool.carpool.entities.User;
import be.carpool.carpool.exceptions.BadRequestException;
import be.carpool.carpool.exceptions.ElementNotFoundException;
import be.carpool.carpool.exceptions.ForeignKeyConstraintViolationException;
import be.carpool.carpool.mappers.LevelMapper;
import be.carpool.carpool.models.dtos.LevelDto;
import be.carpool.carpool.models.dtos.UserSimplifiedDto;
import be.carpool.carpool.models.forms.LevelForm;
import be.carpool.carpool.repositories.LevelRepository;
import be.carpool.carpool.repositories.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;
    private final LevelMapper levelMapper;
    private final UserRepository userRepository;

    public LevelServiceImpl(LevelRepository levelRepository, LevelMapper levelMapper, UserRepository userRepository) {
        this.levelRepository = levelRepository;
        this.levelMapper = levelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<LevelDto> findAll() {
        return levelRepository.findAll()
                .stream()
                .map(levelMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public LevelDto findOne(Long id) throws BadRequestException {
        if(id == null)
            throw new IllegalArgumentException();

        Level level = levelRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Level with id ["+id+"] not found"));

        return levelMapper.entityToDto(level);
    }

    @Override
    @Transactional
    public LevelDto save(LevelForm form) throws BadRequestException {
        if(form == null)
            throw new IllegalArgumentException();

        Level level = levelRepository.save(
                levelMapper.formToEntity(form));

        return levelMapper.entityToDto(level);
    }

    @Override
    @Transactional
    public LevelDto update(LevelForm form, Long id) throws BadRequestException {
        if(form == null || id == null)
            throw new IllegalArgumentException();

        Level level = levelRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Level with id ["+id+"] not found"));

        level.setName(form.getName());

        if(form.getDescription() != null)
            level.setDescription(form.getDescription());

        if(form.getUserIds() != null) {
            List<User> users = new ArrayList<>();;
            for (Long userId : form.getUserIds()) {
                User user = userRepository.findById(userId)
                        .orElseThrow(() -> new ElementNotFoundException("User with id ["+userId+"] not found"));
                users.add(user);
            }
            level.setUsers(users);
        }

        return levelMapper.entityToDto(
                levelRepository.save(level));
    }

    @Override
    @Transactional
    public void delete(Long id) throws BadRequestException, ForeignKeyConstraintViolationException {
        if(id == null)
            throw new IllegalArgumentException();

        Level level = levelRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Level with id ["+id+"] not found"));

        List<User> users = userRepository.findAllByLevelId(id);
        for (User user : users) {
            user.setLevel(null);
            userRepository.save(user);

            try {
                levelRepository.deleteById(id);

            } catch (DataIntegrityViolationException e) {
                if(e.getRootCause() instanceof SQLIntegrityConstraintViolationException) {
                    throw new ForeignKeyConstraintViolationException("record could not be deleted", e.getRootCause().toString());
                } else {
                    throw e;
                }
            }
        }
    }
}
