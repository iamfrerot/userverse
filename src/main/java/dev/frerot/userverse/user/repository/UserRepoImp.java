package dev.frerot.userverse.user.repository;

import dev.frerot.userverse.user.model.NewUser;
import dev.frerot.userverse.user.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserRepoImp implements UserRepo{
    private final MongoTemplate mongoTemplate;

    public UserRepoImp(MongoTemplate mongoTemplate){
        this.mongoTemplate=mongoTemplate;
    }
    @Override
    public List<User> getAllUsers() {
        Query query= new Query();
        query.with(Sort.by(Sort.Direction.ASC,"firstname"));
        return mongoTemplate.find(query,User.class);
    }

    @Override
    public NewUser saveUser(NewUser user) {
        return mongoTemplate.save(user,"users");

    }

    @Override
    public List<User> getAllUsersByCountry(String country) {
        Query query = new Query();
        query.addCriteria(Criteria.where("country").is(country));
        query.with(Sort.by(Sort.Direction.ASC, "firstname"));
        return mongoTemplate.find(query,User.class);
    }
}
