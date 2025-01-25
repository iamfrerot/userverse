package dev.frerot.userverse.userprofile.exceptions;

public class UserProfileExists extends RuntimeException {
    public UserProfileExists(String message) {
        super(message);
    }
}
