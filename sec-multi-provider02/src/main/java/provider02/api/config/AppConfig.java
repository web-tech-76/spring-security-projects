package provider02.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import provider02.api.security.filters.AuthFilter;
import provider02.api.security.providers.AuthProvider;
import provider02.api.services.UserService;

@Configuration
public class AppConfig extends WebSecurityConfigurerAdapter {

    private final AuthProvider authProvider;

    private final AuthFilter authFilter;

    public AppConfig(@Lazy UserService userService,
                        @Lazy AuthProvider authProvider,
                     @Lazy AuthFilter authFilter ) {
        this.authProvider =authProvider;
        this.authFilter=authFilter;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth)  {

        auth.authenticationProvider(authProvider);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .mvcMatchers("/user")
                .permitAll()
                .anyRequest()
                .authenticated();

        http.addFilterAt(authFilter, BasicAuthenticationFilter.class);
    }
}
