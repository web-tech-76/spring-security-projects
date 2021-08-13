package sec_multi_provider.api.config;

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
import sec_multi_provider.api.security.filter.UpAuthFilter;
import sec_multi_provider.api.security.providers.OtpAuthProvider;
import sec_multi_provider.api.security.providers.UserPasswordProvider;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    final UserPasswordProvider userPasswordProvider;
    final OtpAuthProvider otpAuthProvider;
    final UpAuthFilter upAuthFilter;

    public ProjectConfig(@Lazy UserPasswordProvider userPasswordProvider, @Lazy OtpAuthProvider otpAuthProvider,@Lazy UpAuthFilter upAuthFilter) {
        this.userPasswordProvider = userPasswordProvider;
        this.otpAuthProvider = otpAuthProvider;
        this.upAuthFilter= upAuthFilter;
    }

    @Override
    protected void configure(HttpSecurity http)  {
        http.addFilterAt(upAuthFilter, BasicAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)  {
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
