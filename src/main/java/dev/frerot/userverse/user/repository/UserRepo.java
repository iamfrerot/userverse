package dev.frerot.userverse.user.repository;

import dev.frerot.userverse.user.model.User;
import dev.frerot.userverse.utils.RandomUserGenerator;

import java.util.List;

public interface UserRepo {
    List<User> getAllUsers(int page, int size);
    List<User> getAllUsersByCountry(String country);
    User findUserById(String id);
    RandomUserGenerator saveUser();
}
