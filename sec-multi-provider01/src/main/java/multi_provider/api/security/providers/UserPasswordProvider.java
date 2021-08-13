package multi_provider.api.security.providers;

import multi_provider.api.security.authentications.UserPasswordAuthentication;
import multi_provider.api.service.CustomUserDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class UserPasswordProvider implements AuthenticationProvider {

    final CustomUserDetailsService customUserDetailsService;

    final PasswordEncoder passwordEncoder;

    public UserPasswordProvider(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {

        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        UserDetails user = customUserDetailsService.loadUserByUsername(username);

        if (passwordEncoder.matches(password, user.getPassword())) {
            return new UserPasswordAuthentication(username, password, user.getAuthorities());
        }

            throw new BadCredentialsException(":(");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        try {
            Boolean authBool = UsernamePasswordAuthenticationToken.class.equals(aClass);
            System.out.println("is authentication class? = " + authBool);
            return authBool;
        }
        catch(RuntimeException re){
            System.out.println("Exception in supports method  = " + re.getMessage());
            return false;
        }
    }
}
