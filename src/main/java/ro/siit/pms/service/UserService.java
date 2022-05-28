package ro.siit.pms.service;

import ro.siit.pms.entity.User;

import java.util.UUID;

public interface UserService {
    User saveUser(String username, String password);
    User updateUser(User user);
    User getUserById(UUID id);
    void deleteUserById(UUID id);

}
