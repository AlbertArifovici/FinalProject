package ro.siit.pms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.siit.pms.entity.Property;
import ro.siit.pms.entity.User;
import ro.siit.pms.repository.UserRepository;
import ro.siit.pms.service.UserService;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(String username, String password) {
        User entity = new User(username, passwordEncoder.encode(password), "ROLE_USER");
        entity.setUserRole("ROLE_USER");
        return userRepository.save(entity);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }
}
