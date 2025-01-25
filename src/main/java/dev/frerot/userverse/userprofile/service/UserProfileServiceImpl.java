package dev.frerot.userverse.userprofile.service;

import dev.frerot.userverse.userprofile.exceptions.UserProfileExists;
import dev.frerot.userverse.userprofile.exceptions.UserProfileNotFound;
import dev.frerot.userverse.userprofile.model.UserProfile;
import dev.frerot.userverse.userprofile.repository.UserProfileRepo;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepo userProfileRepo;

    public UserProfileServiceImpl(UserProfileRepo userProfileRepo) {
        this.userProfileRepo = userProfileRepo;
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
    public UserProfile saveUserProfile(String userId, UserProfile userProfile) {
        UserProfile found = userProfileRepo.findUserProfileByUserId(userId);
        if(found != null){
            throw new UserProfileExists("User profile already exists with userId: " + userId);
        }
        return userProfileRepo.saveUserProfile(userId, userProfile);
    }
}
