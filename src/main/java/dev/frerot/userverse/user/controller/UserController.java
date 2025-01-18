package dev.frerot.userverse.user.controller;

import dev.frerot.userverse.dto.ErrorResponse;
import dev.frerot.userverse.dto.Login;
import dev.frerot.userverse.dto.SuccessResponse;
import dev.frerot.userverse.user.model.User;
import dev.frerot.userverse.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "User endpoints")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Get all users End point",
            description = "This endpoint returns all users in the database",
            parameters = {
                    @Parameter(
                            name = "page",
                            description = "The page number to return",
                            required = true,
                            content = @Content(schema = @Schema(implementation = int.class))
                    ),
                    @Parameter(
                            name = "size",
                            description = "The number of users to return",
                            required = true,
                            content = @Content(schema = @Schema(implementation = int.class))
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "no users found in chosen range page: {int} size: {int}",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    ),
                    @ApiResponse(
                            responseCode = "200",
                            description = "All users returned successfully",
                            content = {@Content(schema = @Schema(implementation = SuccessResponse.class))}
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    )
            }

    )
    @GetMapping
    @SecurityRequirement(name = "Bearer Auth")
    public ResponseEntity<?> getUsers(@Parameter(required = true) int page, @Parameter(required = true) int size) {
      return userService.findAllUsers(page, size);
    }


    @Operation(
            summary = "Get user by id",
            description = "This endpoint gets a user by id",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "The id of the user to search for",
                            required = true,
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User found successfully",
                            content = {@Content(schema = @Schema(implementation = SuccessResponse.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    )
            }
    )
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer Auth")
    public ResponseEntity<SuccessResponse> getUserById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse(true,HttpStatus.OK.value(),"user found with: "+ id, userService.getUserById(id)));
    }


    @Operation(
            summary = "login a user",
            description = "This endpoint logs in a user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User logged in successfully",
                            content = {@Content(schema = @Schema(implementation = SuccessResponse.class))}
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid Credentials",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    ),
            }
    )
    @PostMapping
    public ResponseEntity<SuccessResponse> login(@Valid @RequestBody Login credentials){

        return ResponseEntity.ok(new SuccessResponse(true, HttpStatus.OK.value(), "User logged in successfully", credentials));
    }
}
