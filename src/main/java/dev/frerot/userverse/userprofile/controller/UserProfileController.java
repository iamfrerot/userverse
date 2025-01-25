package dev.frerot.userverse.userprofile.controller;

import dev.frerot.userverse.dto.ErrorResponse;
import dev.frerot.userverse.dto.SuccessResponse;
import dev.frerot.userverse.userprofile.model.NewUserProfile;
import dev.frerot.userverse.userprofile.model.UserProfile;
import dev.frerot.userverse.userprofile.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/profile")
@Tag(name = "User Profile", description = "User Profile endpoints")
public class UserProfileController {
    private final UserProfileService userProfileService;
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @Operation(
            summary = "Get user profile by user id",
            description = "This endpoint returns user profile by user id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User profile found",
                            content = @Content(schema = @Schema(implementation = SuccessResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User profile not found",
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
    public ResponseEntity<?> getUserProfile(@PathVariable String userid) {
        UserProfile userProfile = userProfileService.getUserProfileByUserId(userid);

        return ResponseEntity.ok(new SuccessResponse(true, HttpStatus.OK.value(),"user profile ",userProfile));
    }

    @Operation(
            summary = "Save user profile",
            description = "This endpoint saves user profile",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User profile saved",
                            content = @Content(schema = @Schema(implementation = SuccessResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User profile already exists",
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
    public ResponseEntity<?> saveUserProfile(@PathVariable String userid, @Valid @RequestBody NewUserProfile userProfile) {
        UserProfile savedUserProfile = userProfileService.saveUserProfile(userid, userProfile);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse(true, HttpStatus.OK.value(), "user profile saved", savedUserProfile));
    }
}
