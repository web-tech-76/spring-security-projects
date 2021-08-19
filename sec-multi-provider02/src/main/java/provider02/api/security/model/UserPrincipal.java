package provider02.api.security.model;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import provider02.api.resources.User;
import provider02.api.resources.UserAccount;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
public class UserPrincipal implements UserDetails {

    private User user;

    private UserAccount userAccount;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "read");
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        String username ="";
        if(null != user.getUsername())
            username=user.getUsername();
        if(null != user.getEmail())
            username= user.getEmail();
        if(null != user.getPhone())
            username= user.getPhone();

        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return (0 != userAccount.getAccExpired())
         ? Boolean.TRUE : Boolean.FALSE;
   }

    @Override
    public boolean isAccountNonLocked() {
        return (0 != userAccount.getAccLock())
                ? Boolean.TRUE : Boolean.FALSE;

    }

    @Override
    public boolean isCredentialsNonExpired() {
        return (0 != userAccount.getCredentialExpired())
                ? Boolean.TRUE : Boolean.FALSE;

    }

    @Override
    public boolean isEnabled() {
        return (0 != userAccount.getEnable())
                ? Boolean.TRUE : Boolean.FALSE;

    }

    }
