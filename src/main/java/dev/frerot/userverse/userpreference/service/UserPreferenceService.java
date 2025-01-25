package dev.frerot.userverse.userpreference.service;

import dev.frerot.userverse.userpreference.model.UserPreference;

import java.util.Optional;

public interface UserPreferenceService {
    Optional<UserPreference> getUserPreferenceByUserId(String userid);
    UserPreference saveUserPreference(String userid,UserPreference userPreference);
}
