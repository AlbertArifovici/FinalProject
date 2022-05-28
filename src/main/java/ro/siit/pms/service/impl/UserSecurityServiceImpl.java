package ro.siit.pms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.siit.pms.entity.User;
import ro.siit.pms.repository.UserRepository;
import ro.siit.pms.service.model.CustomUserDetails;

import java.util.Collections;
import java.util.Set;

@Service
public class UserSecurityServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(
                user.getId(),
                user.getName(),
                user.getPassword(),
                Set.of(new SimpleGrantedAuthority(user.getRole())));
    }
}
