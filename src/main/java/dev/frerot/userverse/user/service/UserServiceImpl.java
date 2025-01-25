package dev.frerot.userverse.user.service;

import dev.frerot.userverse.user.exceptions.UserNotFoundException;
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
    public List<User> findAllUsers(Integer page, Integer size) {
       List<User> users =userRepo.getAllUsers(page, size);
       if(users.isEmpty() && page != null && size != null){
           throw new UserNotFoundException("No users found in range: "+page+" - "+size);
       }
       if(users.isEmpty()) {
           throw new UserNotFoundException("No users found");
       }
         return users;
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
