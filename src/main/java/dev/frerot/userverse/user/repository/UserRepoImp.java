package dev.frerot.userverse.user.repository;

import dev.frerot.userverse.user.model.NewUser;
import dev.frerot.userverse.user.model.User;
import org.springframework.data.mongodb.core.MongoTemplate;
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
        return mongoTemplate.findAll(User.class,"users");
    }

    @Override
    public NewUser saveUser(NewUser user) {
        return mongoTemplate.save(user,"users");

    }
}
