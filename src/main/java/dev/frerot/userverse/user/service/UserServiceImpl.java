package dev.frerot.userverse.user.service;

import dev.frerot.userverse.user.exceptions.UserNotFoundException;
import dev.frerot.userverse.user.model.NewUser;
import dev.frerot.userverse.user.model.User;
import dev.frerot.userverse.user.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;
    public UserServiceImpl(UserRepo userRepo){
        this.userRepo=userRepo;
    }
    @Override
    public List<User> findAllUsers() {
        return userRepo.getAllUsers();
    }

    @Override
    public NewUser addUser(NewUser user) {
        return userRepo.saveUser(user);
    }

    @Override
    public List<User> getUserByCountry(String country) {
        return userRepo.getAllUsersByCountry(country);
    }

    @Override
    public User getUserById(String id) {
        User user=userRepo.findUserById(id);
        if(user != null){
           return user;
        }
        throw new UserNotFoundException("User not found with id: "+id);
    }
}
