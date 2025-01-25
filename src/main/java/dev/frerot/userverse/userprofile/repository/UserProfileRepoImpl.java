package dev.frerot.userverse.userprofile.repository;

import com.github.javafaker.Faker;
import dev.frerot.userverse.userprofile.model.UserProfile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserProfileRepoImpl implements UserProfileRepo {
    Faker faker = new Faker();
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
    public UserProfile saveUserProfile(String userId, UserProfile userProfile) {
        userProfile.setUserid(userId);
        return mongoTemplate.save(userProfile, "userprofile");
    }
}
