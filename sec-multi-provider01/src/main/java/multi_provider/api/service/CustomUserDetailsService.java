package multi_provider.api.service;

import multi_provider.api.entities.User;
import multi_provider.api.repository.UserRepository;
import multi_provider.api.security.model.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var oUser = userRepository.findUserByUsername(username);
        User user = oUser.orElseThrow(() -> new UsernameNotFoundException(":("));
        return new SecurityUser(user);
    }
}
