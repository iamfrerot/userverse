package dev.frerot.userverse.userpreference.repository;

import dev.frerot.userverse.userpreference.model.UserPreference;

import java.util.Optional;

public interface UserPreferenceRepo {
    Optional<UserPreference> findUserPreferenceByUserId(String userid);
    UserPreference saveUserPreference(String userid,UserPreference userPreference);
}
