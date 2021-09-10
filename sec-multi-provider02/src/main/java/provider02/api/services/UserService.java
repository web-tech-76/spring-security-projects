package provider02.api.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import provider02.api.repositories.UserAccountRepository;
import provider02.api.repositories.UserRepository;
import provider02.api.resources.User;
import provider02.api.resources.UserAccount;
import provider02.api.security.model.UserPrincipal;
import provider02.api.util.UserType;

import java.util.Optional;

@Service
@Transactional
public class UserService implements  UserDetailsService {

    private final UserRepository userRepository;

    private final UserAccountRepository userAccountRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            UserAccountRepository userAccountRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.userAccountRepository=userAccountRepository;
        this.passwordEncoder =passwordEncoder;
    }

    private Optional<User> getUserType(String username){
        int type = new UserType().userType(username);
        Optional<User> user = Optional.empty();

        switch (type){
            case 0 :
                user = userRepository.findUserByUsername(username);
                break;
            case 1 :
                user= userRepository.findUserByEmail(username);
                break;
            case 2 :
                user= userRepository.findUserByPhone(username);
                break;
            default:
                break;
        }
        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user= getUserType(username);
        User user1 = user.orElseThrow(() -> new UsernameNotFoundException("no user found "));
        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.setUser(user1);
        return userPrincipal;
    }


    public void createUser(User user){

        // create register record
        String password= passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userRepository.save(user);
        // create user record
        UserAccount userAccount = new UserAccount();
        userAccount.setUserid(user.getId());
        userAccount.setEnable(1);
        userAccountRepository.save(userAccount);

    }




}
