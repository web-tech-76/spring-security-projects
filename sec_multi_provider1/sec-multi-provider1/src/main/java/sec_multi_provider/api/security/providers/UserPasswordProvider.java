package sec_multi_provider.api.security.providers;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sec_multi_provider.api.security.authentications.UserPasswordAuthentication;
import sec_multi_provider.api.service.CustomUserDetailsService;

@Component
public class UserPasswordProvider implements AuthenticationProvider {

    private final CustomUserDetailsService customUserDetailsService;

    private final PasswordEncoder passwordEncoder;

    public UserPasswordProvider(@Lazy CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication)  {

        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        UserDetails user = customUserDetailsService.loadUserByUsername(username);

        if (passwordEncoder.matches(password, user.getPassword())) {
            return new UserPasswordAuthentication(username, password, user.getAuthorities());
        }

            throw new BadCredentialsException(":(");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UserPasswordAuthentication.class.equals(authentication);
    }
}
