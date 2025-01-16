package dev.frerot.userverse.user.service;

import dev.frerot.userverse.user.model.NewUser;
import dev.frerot.userverse.user.model.User;
import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    NewUser addUser(NewUser user);
}
