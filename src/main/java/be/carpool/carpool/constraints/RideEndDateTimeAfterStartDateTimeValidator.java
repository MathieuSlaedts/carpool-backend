package be.carpool.carpool.constraints;

import be.carpool.carpool.entities.Ride;
import be.carpool.carpool.models.forms.RideForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.EnumMap;

public class RideEndDateTimeAfterStartDateTimeValidator implements ConstraintValidator<RideEndDateTimeAfterStartDateTime, RideForm> {
    @Override
    public void initialize(RideEndDateTimeAfterStartDateTime constraintAnnotation) {}

    @Override
    public boolean isValid(RideForm ride, ConstraintValidatorContext context) {
        if(ride == null)
            return true;

        if(ride.getDepartureDate() == null || ride.getReturnDate() == null)
            return true;

        if(ride.getDepartureDate().compareTo(ride.getReturnDate()) < 0) {

            if(ride.getDepartureTime() == null || ride.getReturnTime() == null)
                return true;

            if(ride.getDepartureTime().compareTo(ride.getReturnTime()) < 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
