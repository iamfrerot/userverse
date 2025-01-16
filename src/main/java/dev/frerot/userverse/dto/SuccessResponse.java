package dev.frerot.userverse.dto;

public record SuccessResponse(boolean success, int status, String message, Object data) {
}
