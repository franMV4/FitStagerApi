package org.example.gymapi.exception;

public class GymNotFoundException extends Exception {
    private static final String DEFAULT_ERROR_MESSAGE = "Gym not found";

    public GymNotFoundException(String message) {
        super(message);
    }

    public GymNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }

}
