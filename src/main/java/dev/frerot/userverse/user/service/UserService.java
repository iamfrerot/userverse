package dev.frerot.userverse.user.service;


import dev.frerot.userverse.user.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<?> findAllUsers(int page, int size);
    List<User> getUserByCountry(String country);
    User getUserById(String id);
}
