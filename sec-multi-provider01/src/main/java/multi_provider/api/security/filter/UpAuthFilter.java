package multi_provider.api.security.filter;

import multi_provider.api.entities.Otp;
import multi_provider.api.repository.OtpRepository;
import multi_provider.api.security.authentications.OtpAuthentication;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

@Component
public class UpAuthFilter extends OncePerRequestFilter {

    final OtpRepository otpRepository;

    final AuthenticationManager authenticationManager;

    public UpAuthFilter(AuthenticationManager authenticationManager, OtpRepository otpRepository) {
        this.authenticationManager = authenticationManager;
        this.otpRepository = otpRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String username = request.getHeader("username");
        String password = request.getHeader("password");

        String otp = request.getHeader("otp");
        // step 1: username & password
        // step 2 : username & otp

        if (null == otp) {
            Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
            auth = authenticationManager.authenticate(auth);

            //  generate the otp token
            String code = String.valueOf(new Random().nextInt(9999) + 1000);
            Otp otpEntity = new Otp();
            otpEntity.setUsername(username);
            otpEntity.setOtp(code);
            otpRepository.save(otpEntity);
        } else {
            Authentication auth = new OtpAuthentication(username, otp);
            auth = authenticationManager.authenticate(auth);
            //  issue the otp token
            response.setHeader("Authorization", UUID.randomUUID().toString());
        }
        //chain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }
}
