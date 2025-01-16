package dev.frerot.userverse.dto;

public record ErrorResponse(boolean success,int status, String message, Object errors) {
}
