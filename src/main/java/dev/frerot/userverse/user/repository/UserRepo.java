package dev.frerot.userverse.user.repository;

import dev.frerot.userverse.user.model.NewUser;
import dev.frerot.userverse.user.model.User;

import java.util.List;

public interface UserRepo {
    List<User> getAllUsers();
    NewUser saveUser(NewUser user);
}
