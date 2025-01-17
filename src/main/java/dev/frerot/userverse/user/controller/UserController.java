package dev.frerot.userverse.user.controller;

import dev.frerot.userverse.dto.ErrorResponse;
import dev.frerot.userverse.dto.SuccessResponse;
import dev.frerot.userverse.user.model.NewUser;
import dev.frerot.userverse.user.model.User;
import dev.frerot.userverse.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
            responses = {
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
    public ResponseEntity<SuccessResponse> getUsers(){
        List<User> users =userService.findAllUsers();
        return ResponseEntity.ok(new SuccessResponse(true, HttpStatus.OK.value(), "All users returned successfully",users));
    }

    @Operation(
            summary = "Create a new user",
            description = "This endpoint creates a new user in the database",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "User created successfully",
                            content = {@Content(schema = @Schema(implementation = SuccessResponse.class))}
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    )
            }
    )
    @PostMapping
    public ResponseEntity<SuccessResponse> createUser(@Valid  @RequestBody NewUser user){
        NewUser createdUser = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse(true,HttpStatus.CREATED.value(), "User Created Successfully",createdUser));
    }


    @Operation(
            summary = "Search users by country",
            description = "This endpoint gets all users from a specific country",
            parameters = {
                    @Parameter(
                            name = "country",
                            description = "The country to search for",
                            required = true,
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Users found successfully",
                            content = {@Content(schema = @Schema(implementation = SuccessResponse.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No users found",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    )
            }
    )
    @GetMapping("/search")
    public ResponseEntity<SuccessResponse> getUsersByCountry(@RequestParam String country){
        String countryToUpperCase=country.toUpperCase();
        List<User> usersfromcountry=userService.getUserByCountry(countryToUpperCase);
        String message = "There are: "+ usersfromcountry.size() +" users from "+ countryToUpperCase;
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse(true,HttpStatus.OK.value(),message,userService.getUserByCountry(countryToUpperCase)));
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
    public ResponseEntity<SuccessResponse> getUserById(@PathVariable String id) {
       return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse(true,HttpStatus.OK.value(),"user found with: "+ id, userService.getUserById(id)));
    }
}
