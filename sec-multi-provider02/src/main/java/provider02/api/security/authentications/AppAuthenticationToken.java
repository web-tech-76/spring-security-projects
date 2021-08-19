package provider02.api.security.authentications;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AppAuthenticationToken extends UsernamePasswordAuthenticationToken {


    public AppAuthenticationToken(Object principal, Object credentials) {

        super(principal, credentials);
    }

    public AppAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
