package dev.frerot.userverse.userprofile.repository;

import dev.frerot.userverse.userprofile.model.NewUserProfile;
import dev.frerot.userverse.userprofile.model.UserProfile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserProfileRepoImpl implements UserProfileRepo {
    private final MongoTemplate mongoTemplate;
    public UserProfileRepoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    @Override
    public UserProfile findUserProfileByUserId(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userid").is(userId));
       return mongoTemplate.findOne(query, UserProfile.class);
    }

    @Override
    public UserProfile saveUserProfile(String userId, NewUserProfile user) {
        UserProfile userprofile =  UserProfile.builder()
                .bio(user.getBio())
                .profile_image(user.getProfile_image())
                .userid(userId)
                .website(user.getWebsite())
                .build();
        return mongoTemplate.save(userprofile, "userprofile");
    }
}
