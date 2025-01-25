package dev.frerot.userverse.userprofile.repository;

import dev.frerot.userverse.userprofile.model.UserProfile;

public interface UserProfileRepo {
    public UserProfile findUserProfileByUserId(String userId);
    public UserProfile saveUserProfile(String userId, UserProfile userProfile);
}
