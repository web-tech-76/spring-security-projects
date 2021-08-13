package multi_provider.api.security.authentications;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

public class UserPasswordAuthentication extends UsernamePasswordAuthenticationToken {

    public UserPasswordAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public UserPasswordAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
