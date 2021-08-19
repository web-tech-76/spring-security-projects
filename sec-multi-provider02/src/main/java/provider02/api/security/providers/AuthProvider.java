package provider02.api.security.providers;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import provider02.api.security.authentications.AppAuthenticationToken;
import provider02.api.services.UserService;

@Component
public class AuthProvider implements AuthenticationProvider {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public AuthProvider(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder =passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication)  {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = userService.loadUserByUsername(username);
        if(passwordEncoder.matches(password, userDetails.getPassword()))
          return new AppAuthenticationToken(username, password, userDetails.getAuthorities());

          throw new BadCredentialsException("Bad Credentials :( ");
    }

    @Override
    public boolean supports(Class<? extends  Object> aClass) {
         return AppAuthenticationToken.class.equals(aClass);
    }
}
