package dev.frerot.userverse.user.repository;

import dev.frerot.userverse.user.model.User;
import dev.frerot.userverse.utils.RandomUserGenerator;
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
    public List<User> getAllUsers(int page, int size) {
        Query query= new Query();
        if(page>0 && size>0){
            query.limit(size);
            query.skip((long) (page - 1) * size);
        }
        query.with(Sort.by(Sort.Direction.ASC,"firstname"));
        return mongoTemplate.find(query,User.class);
    }


    @Override
    public List<User> getAllUsersByCountry(String country) {
        Query query = new Query();
        query.addCriteria(Criteria.where("country").is(country));
        query.with(Sort.by(Sort.Direction.ASC, "firstname"));
        return mongoTemplate.find(query,User.class);
    }

    @Override
    public User findUserById(String id) {
      return mongoTemplate.findById(id,User.class);
    }

    @Override
    public RandomUserGenerator saveUser( ) {
        for (int i = 1; i < 100; i++) {
            mongoTemplate.save(new RandomUserGenerator(), "users");
        }
        return mongoTemplate.save(new RandomUserGenerator(),"users");
    }
}
