package be.carpool.carpool.services;

import be.carpool.carpool.entities.Destination;
import be.carpool.carpool.entities.Ride;
import be.carpool.carpool.exceptions.BadRequestException;
import be.carpool.carpool.exceptions.ElementNotFoundException;
import be.carpool.carpool.models.dtos.RideDto;
import be.carpool.carpool.models.forms.RideForm;

import java.time.LocalDate;
import java.util.Set;

public interface RideService extends CrudService<RideForm, RideDto, Long> {
    Set<RideDto> findAllByDepartureCityAndDestinationAndDepartureDate(String departureCity, Long destinationId, LocalDate departureDate) throws ElementNotFoundException;
    Set<RideDto> findAllByDepartureCityAndDestination(String departureCity, Long destinationId) throws ElementNotFoundException;
    Set<RideDto> findAllByDepartureCityAndDepartureDate(String departureCity, LocalDate departureDate);
    Set<RideDto> findAllByDestinationAndDepartureDate(Long destinationId, LocalDate departureDate) throws ElementNotFoundException;
    Set<RideDto> findAllByDepartureCity(String departureCity);
    Set<RideDto> findAllByDestination(Long destinationId) throws ElementNotFoundException;
    Set<RideDto> findAllByDepartureDate(LocalDate departureDate);
}
