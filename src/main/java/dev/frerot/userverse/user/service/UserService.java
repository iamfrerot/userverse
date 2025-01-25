package dev.frerot.userverse.user.service;


import dev.frerot.userverse.user.model.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers(Integer page, Integer size);
    List<User> getUserByCountry(String country);
    User getUserById(String id);
}
