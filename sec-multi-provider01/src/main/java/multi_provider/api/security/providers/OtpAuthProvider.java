package multi_provider.api.security.providers;

import multi_provider.api.repository.OtpRepository;
import multi_provider.api.security.authentications.OtpAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OtpAuthProvider implements AuthenticationProvider {

    @Autowired
    OtpRepository otpRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        var oUser = otpRepository.findOtpByUsername(username);

        if (oUser.isPresent()) {
            return new OtpAuthentication(username, password, List.of(() -> "read"));
        }

        throw new BadCredentialsException(":(");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
