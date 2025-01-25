package dev.frerot.userverse.userpreference.service;

import dev.frerot.userverse.userpreference.exceptions.UserPreferenceExists;
import dev.frerot.userverse.userpreference.exceptions.UserPreferenceNotFound;
import dev.frerot.userverse.userpreference.model.UserPreference;
import dev.frerot.userverse.userpreference.repository.UserPreferenceRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPreferenceServiceImpl implements UserPreferenceService{
    private final UserPreferenceRepo userPreferenceRepo;

    public UserPreferenceServiceImpl(UserPreferenceRepo userPreferenceRepo) {
        this.userPreferenceRepo = userPreferenceRepo;
    }

    @Override
    public Optional<UserPreference> getUserPreferenceByUserId(String userid) {
       Optional<UserPreference> found= userPreferenceRepo.findUserPreferenceByUserId(userid);
         if(found.isEmpty()) {
             throw new UserPreferenceNotFound("UserPreference not found with: "+userid);
         }
        return found;
    }

    @Override
    public UserPreference saveUserPreference(String userid, UserPreference userPreference) {
        if(userPreferenceRepo.findUserPreferenceByUserId(userid).isPresent()){
            throw new UserPreferenceExists("UserPreference already exists with: "+userid);
        }
        return userPreferenceRepo.saveUserPreference(userid,userPreference);
    }
}
