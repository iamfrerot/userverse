package dev.frerot.userverse.userpreference.repository;


import com.github.javafaker.Faker;
import dev.frerot.userverse.userpreference.model.UserPreference;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class UserPreferenceRepoImpl implements UserPreferenceRepo {
    private final MongoTemplate mongoTemplate;

    public UserPreferenceRepoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Optional<UserPreference> findUserPreferenceByUserId(String userid) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userid").is(userid));
        return Optional.ofNullable(mongoTemplate.findOne(query,UserPreference.class));
    }

    @Override
    public UserPreference saveUserPreference(String userid, UserPreference userPreference) {
        userPreference.setUserid(userid);
        return mongoTemplate.save(userPreference,"userpreference");
    }
}
