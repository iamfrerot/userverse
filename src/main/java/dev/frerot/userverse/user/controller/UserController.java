package dev.frerot.userverse.user.controller;

import dev.frerot.userverse.dto.SuccessResponse;
import dev.frerot.userverse.user.model.NewUser;
import dev.frerot.userverse.user.model.User;
import dev.frerot.userverse.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<SuccessResponse> getUsers(){
        List<User> users =userService.findAllUsers();
        return ResponseEntity.ok(new SuccessResponse(true, HttpStatus.OK.value(), "All users",users));
    }
    @PostMapping
    public ResponseEntity<SuccessResponse> createUser(@Valid  @RequestBody NewUser user){
        NewUser createdUser = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse(true,HttpStatus.CREATED.value(), "User Created Successfully",createdUser));
    }
}
