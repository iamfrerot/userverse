package dev.frerot.userverse.userpreference.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.frerot.userverse.user.service.UserService;
import dev.frerot.userverse.userpreference.exceptions.UserPreferenceExists;
import dev.frerot.userverse.userpreference.exceptions.UserPreferenceNotFound;
import dev.frerot.userverse.userpreference.model.NewUserPreference;
import dev.frerot.userverse.userpreference.model.UserPreference;
import dev.frerot.userverse.userpreference.repository.UserPreferenceRepo;

@Service
public class UserPreferenceServiceImpl implements UserPreferenceService{
    private final UserPreferenceRepo userPreferenceRepo;
    private final UserService userService;

    public UserPreferenceServiceImpl(UserPreferenceRepo userPreferenceRepo, UserService userService) {
        this.userPreferenceRepo = userPreferenceRepo;
        this.userService=userService;
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
    public UserPreference saveUserPreference(String userid, NewUserPreference userPreference) {
        userService.getUserById(userid);
        if(userPreferenceRepo.findUserPreferenceByUserId(userid).isPresent()){
            throw new UserPreferenceExists("UserPreference already exists with: "+userid);
        }
        return userPreferenceRepo.saveUserPreference(userid,userPreference);
    }
}
