package dev.frerot.userverse.user.service;

import dev.frerot.userverse.dto.ErrorResponse;
import dev.frerot.userverse.dto.SuccessResponse;
import dev.frerot.userverse.user.exceptions.UserNotFoundException;
import dev.frerot.userverse.user.model.User;
import dev.frerot.userverse.user.repository.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;
    public UserServiceImpl(UserRepo userRepo){
        this.userRepo=userRepo;
    }

    @Override
    public ResponseEntity<?> findAllUsers(int page, int size) {
       List<User> users =userRepo.getAllUsers(page, size);
       if(!users.isEmpty()) {
           return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse(true, HttpStatus.OK.value(),"Users returned successfully",users));
       }
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(false, HttpStatus.NO_CONTENT.value(), "no users found in chosen range"+" page: "+page+" size: "+size, "no users found in chosen range"+" page: "+page+" size: "+size));
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
