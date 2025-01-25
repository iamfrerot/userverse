package dev.frerot.userverse.user.repository;

import dev.frerot.userverse.user.model.User;

import java.util.List;

public interface UserRepo {
    List<User> getAllUsers(Integer page, Integer size);
    List<User> getAllUsersByCountry(String country);
    User findUserById(String id);
}
