package provider02.api.controllers;

import com.sun.istack.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import provider02.api.resources.User;
import provider02.api.services.UserService;

@RestController
@RequestMapping("/")
public class LoginController {

    final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/user")
    @ResponseBody
    public String registration(@NotNull @RequestBody User user)
    {
        logger.trace("Enter registration");
        userService.createUser(user);
        logger.trace("Exit registration");

        return "user created successfully";
    }

    @GetMapping("/otp")
    @ResponseBody
    public String otp()
    {
        return "otp generated";
    }


}
