package provider02.api.services;

import org.springframework.stereotype.Service;
import provider02.api.repositories.OtpRepository;
import provider02.api.repositories.UserRepository;
import provider02.api.resources.Otp;
import provider02.api.resources.User;
import provider02.api.util.OtpUtil;
import provider02.api.util.UserType;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {

    private final OtpRepository otpRepository;

    private final UserRepository userRepository;

    public OtpService(OtpRepository otpRepository, UserRepository userRepository) {
        this.otpRepository = otpRepository;
        this.userRepository = userRepository;
    }

    private User getUserType(String username){

        int type = new UserType().userType(username);
        User user = null;
        Optional<User> optionalUser;

        switch (type){
            case 0 :
                optionalUser= userRepository.findUserByUsername(username);
                user = (optionalUser.orElse(null));
                break;
            case 1 :
                optionalUser= userRepository.findUserByEmail(username);
                user = (optionalUser.orElse(null));
                break;
            case 2 :
                optionalUser= userRepository.findUserByPhone(username);
                user = (optionalUser.orElse(null));
                break;
            default:
                break;
        }
            return user;
    }

    public int generateOtpAndSave(String username) {
        int otpCode= 0;
        int id;
        var timestamp= new Timestamp(System.currentTimeMillis() + TimeUnit.MINUTES.toSeconds(360));

        id = getUserType(username).getId();
        //System.out.println(" id to send for otp check = " + id);

        if(id !=0 && null == getUserOtp(id)) {
            Otp otp = new Otp();
            otp.setUserid(id);
            otpCode=new OtpUtil().generateIntOtp(9999);
            otp.setCode(String.valueOf(otpCode));
            otp.setExpiry(timestamp);
            otpRepository.save(otp);
        }

        //System.out.println(" otp code for user id  = " + id + " is = " + otpCode);
        return otpCode;
    }

    public String getUserOtp(int userid){
        Timestamp timestamp= new Timestamp(System.currentTimeMillis());
        Optional<Otp> optionalOtp= otpRepository.findOtpByUseridAndExpiry(userid,timestamp);
        Otp otpBean=  optionalOtp.orElse(null);

        if(null!=otpBean) {
          //  System.out.println("otpBean.getCode() = " + otpBean.getCode());
            return otpBean.getCode();
        }

        return null;
    }

}
