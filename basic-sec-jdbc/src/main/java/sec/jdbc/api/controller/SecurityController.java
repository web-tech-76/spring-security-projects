package sec.jdbc.api.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;
import sec.jdbc.api.resources.Users;

@RestController
@RequestMapping("/")
public class SecurityController {

    private final JdbcUserDetailsManager jdbcUserDetailsManager;

    private PasswordEncoder passwordEncoder;

    public SecurityController(JdbcUserDetailsManager jdbcUserDetailsManager , PasswordEncoder passwordEncoder) {
        this.jdbcUserDetailsManager = jdbcUserDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("test/")
    public String find() {
        return "basic security jdbc test";
    }

    @PostMapping("user/")
    public void addNew(@RequestBody Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        jdbcUserDetailsManager.createUser(users);
    }
}
