package be.carpool.carpool.controller;

import be.carpool.carpool.exceptions.WrongPathVariableTypeException;

public final class ControllerUtilities {

    private ControllerUtilities() {
        throw new UnsupportedOperationException();
    }

    public static Long StringToLong(String s) throws WrongPathVariableTypeException {
        try {
            return Long.parseLong(s);
        } catch(NumberFormatException e) {
            throw new WrongPathVariableTypeException();
        }
    }
}