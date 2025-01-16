package dev.frerot.userverse.user.repository;

import dev.frerot.userverse.user.model.NewUser;
import dev.frerot.userverse.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepo {
    List<User> getAllUsers();
    NewUser saveUser(NewUser user);
    List<User> getAllUsersByCountry(String country);
    User findUserById(String id);
}
