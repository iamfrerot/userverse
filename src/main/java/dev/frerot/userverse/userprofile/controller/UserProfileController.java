package dev.frerot.userverse.userprofile.controller;

import dev.frerot.userverse.dto.SuccessResponse;
import dev.frerot.userverse.userprofile.model.UserProfile;
import dev.frerot.userverse.userprofile.service.UserProfileService;
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

    @GetMapping("/{userid}")
    public ResponseEntity<?> getUserProfile(@PathVariable String userid) {
        UserProfile userProfile = userProfileService.getUserProfileByUserId(userid);

        return ResponseEntity.ok(new SuccessResponse(true, HttpStatus.OK.value(),"user profile ",userProfile));
    }

    @PostMapping("/{userid}")
    public ResponseEntity<?> saveUserProfile(@PathVariable String userid, @Valid @RequestBody UserProfile userProfile) {
        UserProfile savedUserProfile = userProfileService.saveUserProfile(userid, userProfile);
        return ResponseEntity.ok(new SuccessResponse(true, HttpStatus.OK.value(), "user profile saved", savedUserProfile));
    }
}
