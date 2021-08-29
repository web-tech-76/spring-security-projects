package multi_provider.api.config;

import multi_provider.api.security.filter.UpAuthFilter;
import multi_provider.api.security.providers.OtpAuthProvider;
import multi_provider.api.security.providers.UserPasswordProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    private UserPasswordProvider userPasswordProvider;
    private  OtpAuthProvider otpAuthProvider;
    private final UpAuthFilter upAuthFilter;

    @Autowired
    public void setUserPasswordProvider(UserPasswordProvider userPasswordProvider){
        this.userPasswordProvider = userPasswordProvider;
    }



    @Autowired
    public void setOtpAuthProvider(OtpAuthProvider otpAuthProvider){
        this.otpAuthProvider = otpAuthProvider;
    }


    public ProjectConfig(@Lazy UpAuthFilter upAuthFilter) {
        this.upAuthFilter = upAuthFilter;
        //this.userPasswordProvider = userPasswordProvider;
        //this.otpAuthProvider = otpAuthProvider;
     }

    @Override
    protected void configure(HttpSecurity http) {
        http.addFilterAt(upAuthFilter, BasicAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(userPasswordProvider)
            .authenticationProvider(otpAuthProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
