package dev.frerot.userverse.userprofile.service;

import dev.frerot.userverse.user.service.UserService;
import dev.frerot.userverse.userprofile.exceptions.UserProfileExists;
import dev.frerot.userverse.userprofile.exceptions.UserProfileNotFound;
import dev.frerot.userverse.userprofile.model.NewUserProfile;
import dev.frerot.userverse.userprofile.model.UserProfile;
import dev.frerot.userverse.userprofile.repository.UserProfileRepo;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepo userProfileRepo;
    private final UserService userService;
    public UserProfileServiceImpl(UserProfileRepo userProfileRepo, UserService userService) {
        this.userProfileRepo = userProfileRepo;
        this.userService=userService;
    }

    @Override
    public UserProfile getUserProfileByUserId(String userId) {
        UserProfile found = userProfileRepo.findUserProfileByUserId(userId);
        if(found ==null){
            throw new UserProfileNotFound("User profile not found with userId: " + userId);
        }
        return found;
    }

    @Override
    public UserProfile saveUserProfile(String userId, NewUserProfile userProfile) {
        userService.getUserById(userId);
        UserProfile found = userProfileRepo.findUserProfileByUserId(userId);
        if(found != null){
            throw new UserProfileExists("User profile already exists with userId: " + userId);
        }
        return userProfileRepo.saveUserProfile(userId, userProfile);
    }
}
