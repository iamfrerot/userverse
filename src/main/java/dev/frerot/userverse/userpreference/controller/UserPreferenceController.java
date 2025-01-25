package dev.frerot.userverse.userpreference.controller;

import dev.frerot.userverse.dto.SuccessResponse;
import dev.frerot.userverse.userpreference.model.UserPreference;
import dev.frerot.userverse.userpreference.service.UserPreferenceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/preferences")
public class UserPreferenceController {
    public final UserPreferenceService userPreferenceService;

    public UserPreferenceController(UserPreferenceService userPreferenceService) {
        this.userPreferenceService = userPreferenceService;
    }

    @GetMapping("/{userid}")
    public ResponseEntity<?> getUserPreferenceByUserId(@PathVariable String userid){
        return ResponseEntity.ok(new SuccessResponse(true, HttpStatus.OK.value(), "user preference found",userPreferenceService.getUserPreferenceByUserId(userid)));
    }

    @PostMapping("/{userid}")
    public ResponseEntity<?> saveUserPreference(@PathVariable String userid, @Valid @RequestBody UserPreference userPreference
    ){
        return ResponseEntity.ok(new SuccessResponse(true, HttpStatus.OK.value(), "user preference saved",userPreferenceService.saveUserPreference(userid,userPreference)));
    }
}
