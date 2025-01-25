package dev.frerot.userverse.userprofile.service;

import dev.frerot.userverse.userprofile.model.UserProfile;

public interface UserProfileService {
    public UserProfile getUserProfileByUserId(String userId);
    public UserProfile saveUserProfile(String userId, UserProfile userProfile);
}
