package provider02.api.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import provider02.api.services.UserService;

@WebMvcTest
class LoginControllerTest {

    @Mock
    LoginController loginController;

    @MockBean
    UserService userService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void registration() {


    }

    @Test
    void otp() {

    }
}
