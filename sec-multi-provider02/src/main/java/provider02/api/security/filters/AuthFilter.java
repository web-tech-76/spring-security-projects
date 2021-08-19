package provider02.api.security.filters;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import provider02.api.security.authentications.AppAuthenticationToken;
import provider02.api.services.OtpService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter {

    private final OtpService otpService;
    private final AuthenticationManager authenticationManager;

    public AuthFilter(OtpService otpService,
                      AuthenticationManager authenticationManager) {
        this.otpService = otpService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) {

        String username = request.getHeader("username");
        String password = request.getHeader("password");
        String otp = request.getHeader("otp");

        Authentication auth= new AppAuthenticationToken(username, password);

        if(null == otp){
              auth = authenticationManager.authenticate(auth);

              int code = otpService.generateOtpAndSave(username);
              // send otp to client sms or email
            if(code ==0 ) {
                System.out.println(" Otp already generated .. wait 1 minute to generate new");
            }
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        boolean flag= false;

        if("/user/".equals(path)) {
            flag= true;
        }
        else if(!("/user/".equals(path))) {
            flag= false;
        }
        return flag;
    }
}
