package dev.frerot.userverse.user.service;

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
}
