package auth.api.config;

import auth.api.providers.AppAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private AppAuthProvider appAuthProvider;

    @Autowired
    public void setAppAuthProvider(AppAuthProvider appAuthProvider) {
        this.appAuthProvider = appAuthProvider;
    }

    /*public SecurityConfig(@Lazy  AppAuthProvider appAuthProvider) {
        this.appAuthProvider = appAuthProvider;
    }*/

    @Bean
    public UserDetailsManager userDetailsManager() {
        var userDetailsManager = new InMemoryUserDetailsManager();
        var userDetails = User
                .withUsername("me")
                .password("1234")
                .authorities("read")
                .build();
        userDetailsManager.createUser(userDetails);
        return userDetailsManager;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(appAuthProvider);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
