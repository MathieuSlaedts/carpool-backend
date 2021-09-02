package be.carpool.carpool.services;

import be.carpool.carpool.entities.Destination;
import be.carpool.carpool.entities.Ride;
import be.carpool.carpool.exceptions.BadRequestException;
import be.carpool.carpool.exceptions.ElementNotFoundException;
import be.carpool.carpool.models.dtos.RideDto;
import be.carpool.carpool.models.forms.RideForm;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface RideService extends CrudService<RideForm, RideDto, Long> {
    List<RideDto> findAllByMessagesSenderId(Long id) throws ElementNotFoundException;

    List<RideDto> findRidesInFuture(String departureCity, Long destinationId, LocalDate departureDate) throws ElementNotFoundException;
    List<RideDto> findRidesInFutureByConductor(Long conductorId) throws ElementNotFoundException;
    List<RideDto> findRidesInFutureByPassenger(Long passengerId) throws ElementNotFoundException;
    List<RideDto> findRidesInPastByConductor(Long conductorId) throws ElementNotFoundException;
    List<RideDto> findRidesInPastByPassenger(Long conductorId) throws ElementNotFoundException;


    List<RideDto> findAllByDepartureCityAndDestinationAndDepartureDate(String departureCity, Long destinationId, LocalDate departureDate) throws ElementNotFoundException;
    List<RideDto> findAllByDepartureCityAndDestination(String departureCity, Long destinationId) throws ElementNotFoundException;
    List<RideDto> findAllByDepartureCityAndDepartureDate(String departureCity, LocalDate departureDate);
    List<RideDto> findAllByDestinationAndDepartureDate(Long destinationId, LocalDate departureDate) throws ElementNotFoundException;
    List<RideDto> findAllByDepartureCity(String departureCity);
    List<RideDto> findAllByDestination(Long destinationId) throws ElementNotFoundException;
    List<RideDto> findAllByDepartureDate(LocalDate departureDate);
}
