package sec.jdbc.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public JdbcUserDetailsManager appJdbcUserManager() {
        return new JdbcUserDetailsManager(dataSource());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl("jdbc:mysql://localhost/security-db");
        dataSource.setUsername("dbuser");
        dataSource.setPassword("passw0rd");
        return dataSource;

    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic();

        http.csrf().disable();

        http.authorizeRequests()
                .mvcMatchers("/user")
                .permitAll()
                .anyRequest()
                .authenticated();

    }



}
