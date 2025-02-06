package dev.frerot.userverse.userpreference.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.frerot.userverse.dto.ErrorResponse;
import dev.frerot.userverse.dto.SuccessResponse;
import dev.frerot.userverse.userpreference.model.NewUserPreference;
import dev.frerot.userverse.userpreference.service.UserPreferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user/preference")
@Tag(name = "User Preference", description = "User Preference endpoints")
public class UserPreferenceController {
    public final UserPreferenceService userPreferenceService;

    public UserPreferenceController(UserPreferenceService userPreferenceService) {
        this.userPreferenceService = userPreferenceService;
    }

    @Operation(
            summary = "Get user preference by user id",
            description = "This endpoint returns user preference by user id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User preference found",
                            content = @Content(schema = @Schema(implementation = SuccessResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User preference not found",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @GetMapping("/{userid}")
    public ResponseEntity<?> getUserPreferenceByUserId(@PathVariable String userid){
        return ResponseEntity.ok(new SuccessResponse(true, HttpStatus.OK.value(), "user preference found",userPreferenceService.getUserPreferenceByUserId(userid)));
    }
    @Operation(
            summary = "Save user preference",
            description = "This endpoint saves user preference",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User preference saved",
                            content = @Content(schema = @Schema(implementation = SuccessResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "User preference already exists",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @PostMapping("/{userid}")
    public ResponseEntity<?> saveUserPreference(@PathVariable String userid, @Valid @RequestBody NewUserPreference userPreference
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse(true, HttpStatus.CREATED.value(), "user preference saved",userPreferenceService.saveUserPreference(userid,userPreference)));
    }
}
