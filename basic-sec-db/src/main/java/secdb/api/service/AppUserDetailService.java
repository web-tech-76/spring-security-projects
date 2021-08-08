package secdb.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import secdb.api.repository.UserRepository;
import secdb.api.resource.User;

import java.util.Optional;

public class AppUserDetailService implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        Optional<User> user = userRepository.findByUsername(username);

        User u = user.orElseThrow( () -> new UsernameNotFoundException("user not found !"));

        return new SecurityUserDetails(u);
    }
}
